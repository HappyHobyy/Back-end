package org.v1.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.v1.domain.user.domain.User;
import org.v1.domain.user.implementation.UserReader;
import org.v1.domain.user.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    @Mock
    private UserReader userReader;
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
                null
        );
    }
}
