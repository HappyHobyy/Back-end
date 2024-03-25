package domain.user;

import org.example.domain.user.domain.User;
import org.example.domain.user.implementation.UserAppender;
import org.example.domain.user.implementation.UserChecker;
import org.example.domain.user.implementation.UserReader;
import org.example.domain.user.service.UserServiceImpl;
import org.example.error.BusinessException;
import org.example.error.ErrorCode;
import org.example.global.config.security.password.PasswordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    private UserAppender userAppender;
    @Mock
    private UserReader userReader;
    @Mock
    private UserChecker userChecker;
    @Mock
    private PasswordService passwordService;
    @InjectMocks
    private UserServiceImpl userService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("회원가입 실패 후 예외처리")
    void registerUser_WithNonDuplicateEmail_ShouldRegisterUser() {
        // given
        User user = User.builder().build();
        when(userChecker.isUserEmailDuplicate(user)).thenReturn(false);

        //when
        userService.registerUser(user);

        // then
        verify(passwordService).encodePassword(user);
        verify(userAppender).appendUser(user);
    }

    @Test
    @DisplayName("회원가입 성공")
    void registerUser_WithDuplicateEmail_ShouldThrowException(){
        //given
        User user = User.builder().build();
        when(userChecker.isUserEmailDuplicate(user)).thenReturn(true);

        //when&then
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            userService.registerUser(user);
        });
        assertEquals(ErrorCode.USER_REGISTER_EMAIL_FAIL, exception.getErrorCode());
    }

    @Test
    @DisplayName("틀린 비밀번호 확인 후 예외처리")
    void loginUser_WithIncorrectPassword_ShouldThrowException() {
        // given
        User user = User.builder().build();;
        User savedUser = User.builder().password("encodedPassword").build();
        when(userReader.readUserByTypeAndEmail(user)).thenReturn(savedUser);
        when(passwordService.matchPassword(any(), any())).thenReturn(false);
        //when
        BusinessException exception = assertThrows(BusinessException.class, () -> userService.loginUser(user));
        //then
        assertEquals(ErrorCode.USER_LOGIN_PASSWORD_FAIL, exception.getErrorCode());
    }
    @Test
    @DisplayName("새로운 카카오 유저 로그인 성공")
    void loginUserWithKakao_NewUser_ShouldAppendAndReturnUser() {
        // Given
        User user = User.builder().build();
        when(userChecker.isUserEmailDuplicate(user)).thenReturn(false);
        when(userReader.readUserByTypeAndEmail(user)).thenReturn(user);

        // When
        User result = userService.loginUserWithKakao(user);

        // Then
        verify(userAppender).appendUser(user);
        assertEquals(user, result);
    }

    @Test
    @DisplayName("기존 카카오 유저 로그인 성공")
    void loginUserWithKakao_ExistingUser_ShouldReturnUser() {
        // Given
        User user = User.builder().build();
        when(userChecker.isUserEmailDuplicate(user)).thenReturn(true);
        when(userReader.readUserByTypeAndEmail(user)).thenReturn(user);

        // When
        User result = userService.loginUserWithKakao(user);

        // Then
        verify(userAppender, never()).appendUser(user);
        assertEquals(user, result);
    }

    @Test
    @DisplayName("새로운 구글 유저 로그인 성공")
    void loginUserWithGoogle_NewUser_ShouldAppendAndReturnUser() {
        // Given
        User user = User.builder().build();
        when(userChecker.isUserEmailDuplicate(user)).thenReturn(false);
        when(userReader.readUserByTypeAndEmail(user)).thenReturn(user);

        // When
        User result = userService.loginUserWithGoogle(user);

        // Then
        verify(userAppender).appendUser(user);
        assertEquals(user, result);
    }

    @Test
    @DisplayName("기존 구글 유저 로그인 성공")
    void loginUserWithGoogle_ExistingUser_ShouldReturnUser() {
        // Given
        User user = User.builder().build();
        when(userChecker.isUserEmailDuplicate(user)).thenReturn(true);
        when(userReader.readUserByTypeAndEmail(user)).thenReturn(user);

        // When
        User result = userService.loginUserWithGoogle(user);

        // Then
        verify(userAppender, never()).appendUser(user);
        assertEquals(user, result);
    }
}
