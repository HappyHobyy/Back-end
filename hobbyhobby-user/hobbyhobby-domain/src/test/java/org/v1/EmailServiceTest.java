package org.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.v1.implementation.EmailSender;
import org.v1.implementation.UserAppender;
import org.v1.implementation.UserReader;
import org.v1.model.Mail;
import org.v1.model.User;
import org.v1.service.EmailService;
import org.v1.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EmailServiceTest {
    @Mock
    private UserReader userReader;
    @Mock
    private UserAppender userAppender;
    @Mock
    private EmailSender emailSender;
    @InjectMocks
    private EmailService emailService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void sendEmailAndChangePassword_Success() {
        // Given
        String userEmail = "example@example.com";
        User mockUser = createUser();
        // When
        when(userReader.readUserByEmail(userEmail)).thenReturn(mockUser);

        emailService.sendEmailAndChangePassword(userEmail);
        // Verify interactions
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userAppender).appendUser(captor.capture());

        ArgumentCaptor<Mail> mailCaptor = ArgumentCaptor.forClass(Mail.class);
        verify(emailSender).sendEmail(mailCaptor.capture());

        assertNotEquals(mockUser.getPassword().password(), captor.getValue().getPassword());
        Mail capturedMail = mailCaptor.getValue();
        assertEquals(mockUser.getEmail(), capturedMail.address());
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
                "testImageUrl",
                "testName",
                123
        );
    }
}
