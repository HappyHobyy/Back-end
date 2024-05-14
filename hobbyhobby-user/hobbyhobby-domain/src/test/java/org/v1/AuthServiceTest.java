package org.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.v1.error.BusinessException;
import org.v1.error.ErrorCode;
import org.v1.implementation.*;
import org.v1.model.User;
import org.v1.service.AuthService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class AuthServiceTest {
    @Mock
    private UserChecker userChecker;
    @Mock
    private UserAppender userAppender;
    @Mock
    private UserReader userReader;
    @Mock
    private UserUpdater userUpdater;
    @Mock
    private UserValidator userValidator;
    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("회원가입 성공")
    void registerUser_WithNonDuplicateEmail_ShouldRegisterUser() {
        // given
        User user = createUser1();
        //when
        authService.registerDefaultUser(user);
        // then
        verify(userAppender).appendUser(any(User.class));
    }




    @Test
    @DisplayName("기존 소셜 유저 로그인 성공")
    void loginUserWithOAuth_ExistingUser_ShouldReturnUser() {
        // Given
        User user = createUser1();
        User savedUser = createUser2();
        when(userReader.readUserByEmail(savedUser.getEmail())).thenReturn(user);
        // When
        User result = authService.loginOAuthUser(savedUser);
        // Then
        assertNotNull(result.getDeviceToken());
        assertNull(result.getPassword());
    }


    User createUser1() {
        return User.withId(
                new User.UserId(123L),
                "testNickname",
                "testEmail",
                User.UserType.OAUTH_DEFAULT,
                new User.Password("testPassword"),
                User.UserRole.ROLE_USER,
                User.UserGender.MAN,
                User.Nationality.DOMESTIC,
                "123",
                "123",
                "testName",
                123,
                "2001/12/16"
        );
    }

    User createUser2() {
        return User.withId(
                new User.UserId(123L),
                "testNickname",
                "testEmail",
                User.UserType.OAUTH_DEFAULT,
                new User.Password("testPassword"),
                User.UserRole.ROLE_USER,
                User.UserGender.MAN,
                User.Nationality.DOMESTIC,
                "testDeviceToken",
                "123",
                "testName",
                123,
                "2001/12/16"
        );
    }
}
