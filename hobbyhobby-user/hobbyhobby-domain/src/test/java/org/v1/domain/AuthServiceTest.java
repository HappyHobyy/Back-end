package org.v1.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.v1.domain.auth.AuthService;
import org.v1.domain.user.domain.User;
import org.v1.domain.user.implementation.UserAppender;
import org.v1.domain.user.implementation.UserChecker;
import org.v1.domain.user.implementation.UserReader;
import org.v1.error.BusinessException;
import org.v1.error.ErrorCode;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceTest {
    @Mock
    private UserChecker userChecker;
    @Mock
    private UserAppender userAppender;
    @Mock
    private UserReader userReader;
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
        when(userChecker.isUserEmailDuplicate(user)).thenReturn(false);
        //when
        authService.registerDefaultUser(user);
        // then
        verify(userAppender).appendUser(any(User.class));
    }

    @Test
    @DisplayName("회원가입 실패")
    void registerUser_WithDuplicateEmail_ShouldThrowException() {
        //given
        User user = createUser1();
        when(userChecker.isUserEmailDuplicate(user)).thenReturn(true);
        //when&then
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            authService.registerDefaultUser(user);
        });
        assertEquals(ErrorCode.USER_EMAIL_DUPLICATED, exception.getErrorCode());
    }

    @Test
    @DisplayName("틀린 비밀번호 확인 후 예외처리")
    void loginUser_WithIncorrectPassword_ShouldThrowException() {
        // given
        User savedUser = createUser2();
        User user = createUser1();
        when(userReader.readUserByTypeAndEmail(user)).thenReturn(savedUser);
        //when
        BusinessException exception = assertThrows(BusinessException.class, () -> authService.loginDefaultUser(user));
        //then
        assertEquals(ErrorCode.USER_LOGIN_PASSWORD_FAIL, exception.getErrorCode());
    }

    @Test
    @DisplayName("기존 소셜 유저 로그인 성공")
    void loginUserWithOAuth_ExistingUser_ShouldReturnUser() {
        // Given
        User user = User.withId(
                new User.UserId(123L),
                "testNickname",
                "testEmail",
                User.UserType.OAUTH_KAKAO,
                null,
                User.UserRole.ROLE_USER,
                User.UserGender.MAN,
                User.Nationality.DOMESTIC,
                null,
                null
        );
        User savedUser = createUser2();
        when(userReader.readUserByTypeAndEmail(savedUser)).thenReturn(user);
        // When
        User result = authService.loginOAuthUser(savedUser);
        // Then
        assertNotNull(result.getDeviceToken());
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
                null,
                null
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
                null
        );
    }
}
