package domain.user;

import org.v1.domain.user.domain.User;
import org.v1.domain.user.implementation.UserAppender;
import org.v1.domain.user.implementation.UserChecker;
import org.v1.domain.user.implementation.UserReader;
import org.v1.domain.user.implementation.UserRemover;
import org.v1.domain.user.service.UserServiceImpl;
import org.v1.error.BusinessException;
import org.v1.error.ErrorCode;
import org.v1.global.config.security.password.PasswordService;
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
    private UserReader userReader;
    @InjectMocks
    private UserServiceImpl userService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("마이페이지 읽기 성공")
    void getMyPageWithUserId_ShouldReturnUser () {
        // Given
        Long userId = 1L;
        User savedUser = User.builder().id(1L).build();
        when(userReader.readById(userId)).thenReturn(savedUser);
        // When
        User result = userService.getMyPage(userId);
        // Then
        assertEquals(userId, result.getId());
    }
}
