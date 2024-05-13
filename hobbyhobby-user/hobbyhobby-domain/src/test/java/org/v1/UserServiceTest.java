package org.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.v1.implementation.*;
import org.v1.model.User;
import org.v1.service.UserService;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    private UserReader userReader;
    @Mock
    private UserRemover userRemover;
    @Mock
    private UserUpdater userUpdater;
    @Mock
    private ImageAppender imageAppender;
    @Mock
    private ImageRemover imageRemover;
    @InjectMocks
    private UserService userService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("마이페이지 읽기 성공")
    void getMyPageWithUserId_ShouldReturnUser () {
        // Given
        Long userId = 123L;
        User savedUser = createUser();
        when(userReader.readById(userId)).thenReturn(savedUser);
        // When
        User result = userService.getMyPage(userId);
        // Then
        assertEquals(userId, result.getId().value());
    }
    @Test
    @DisplayName("유저 삭제 성공")
    void savedUser () {
        // Given
        Long userId = 123L;
        User savedUser = createUser();
        when(userReader.readById(userId)).thenReturn(savedUser);
        // When
        userService.removeUser(userId);
        // Then
    }
    @Test
    public void testChangeProfileImage() {
        //Given
        File file = new File("testImage.jpg");
        Long userId = 1L;
        User user = createUser();
        //When
        when(userReader.readById(userId)).thenReturn(user);
        doNothing().when(imageRemover).removeImage(anyString());
        when(imageAppender.appendImage(file, userId.toString())).thenReturn("newImageUrl");
        userService.changeProfileImage(file, userId);
        verify(imageRemover, times(1)).removeImage("testImageUrl");
        verify(userUpdater, times(1)).updateUser(argThat(updatedUser ->
                updatedUser.getImageUrl().equals("newImageUrl")
        ));
    }

    User createUser(){
        return  User.withId(
                new User.UserId(123L),
                "testNickname",
                "testEmail",
                User.UserType.OAUTH_DEFAULT,
                new User.Password("testPassword"),
                User.UserRole.ROLE_USER,
                User.UserGender.MAN,
                User.Nationality.DOMESTIC,
                "testDeviceToken",
                "testImageUrl"
        );
    }
}
