package org.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.v1.handler.ExternalCommunitySender;
import org.v1.handler.ExternalPhotoContentSender;
import org.v1.handler.ExternalTextContentSender;
import org.v1.implementation.UserUpdater;
import org.v1.model.User;
import org.v1.repository.UserRepository;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public
class UserUpdaterTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ExternalPhotoContentSender externalPhotoContentSender;

    @Mock
    private ExternalTextContentSender externalTextContentSender;

    @Mock
    private ExternalCommunitySender externalCommunitySender;

    @InjectMocks
    private UserUpdater userUpdater;
    private User sampleUser;

    @BeforeEach
    public void setup() {
        sampleUser = User.withId(
                new User.UserId(123L),
                "testNickname",
                "testEmail",
                User.UserType.OAUTH_DEFAULT,
                new User.Password("testPassword"),
                User.UserGender.MAN,
                User.Nationality.DOMESTIC,
                "testDeviceToken",
                "testImageUrl",
                "testName",
                "01011112222",
                "2001/12/16"
        );
    }

    @Test
    public void testUpdateUser() {
        // When
        userUpdater.updateUser(sampleUser);

        // Then
        verify(userRepository, times(1)).update(sampleUser);
        verify(externalPhotoContentSender, times(1)).sendUserUpdate(sampleUser);
        verify(externalTextContentSender, times(1)).sendUserUpdate(sampleUser);
        verify(externalCommunitySender, times(1)).sendUserUpdate(sampleUser);
    }
}
