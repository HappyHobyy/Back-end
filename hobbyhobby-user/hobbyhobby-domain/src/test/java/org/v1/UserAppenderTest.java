package org.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.v1.error.BusinessException;
import org.v1.error.ErrorCode;
import org.v1.handler.ExternalPhotoContentSender;
import org.v1.handler.ExternalTextContentSender;
import org.v1.implementation.UserAppender;
import org.v1.model.User;
import org.v1.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserAppenderTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ExternalPhotoContentSender externalPhotoContentSender;

    @Mock
    private ExternalTextContentSender externalTextContentSender;

    @InjectMocks
    private UserAppender userAppender;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    private User sampleUser;

    @BeforeEach
    public void setup() {
        sampleUser = User.withId(
                new User.UserId(123L),
                "testNickname",
                "testEmail",
                User.UserType.OAUTH_DEFAULT,
                new User.Password("testPassword"),
                User.UserRole.ROLE_USER,
                User.UserGender.MAN,
                User.Nationality.DOMESTIC,
                "testDeviceToken",
                "testImageUrl",
                "testName",
                123,
                "2001/12/16"
        );
    }

    @Test
    public void testAppendUser_Success() {
        //Given
        when(userRepository.appendUser(any(User.class))).thenReturn(Optional.of(sampleUser));
        //When
        userAppender.appendUser(sampleUser);
        //Then
        verify(userRepository).appendUser(userCaptor.capture());

        verify(externalPhotoContentSender).sendUserCreate(sampleUser);
        verify(externalTextContentSender).sendUserCreate(sampleUser);
    }

    @Test
    public void testAppendUser_Failure() {
        //Given
        when(userRepository.appendUser(any(User.class))).thenReturn(Optional.empty());
        //When
        assertThrows(BusinessException.class, () -> userAppender.appendUser(sampleUser));
        //Then
        verifyNoInteractions(externalPhotoContentSender);
        verifyNoInteractions(externalTextContentSender);
    }
}
