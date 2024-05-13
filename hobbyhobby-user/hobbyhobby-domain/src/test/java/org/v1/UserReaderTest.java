package org.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.v1.error.BusinessException;
import org.v1.error.ErrorCode;
import org.v1.implementation.UserReader;
import org.v1.model.User;
import org.v1.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserReaderTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserReader userReader;
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
                "testImageUrl"
        );
    }

    @Test
    @DisplayName("userId로 id 읽기 성공")
    public void testReadById() {
        // Given
        Long userId = 1L;
        when(userRepository.readById(userId)).thenReturn(Optional.of(sampleUser));

        // When
        User result = userReader.readById(userId);

        // Then
        assertEquals(sampleUser, result);
    }

    @Test
    @DisplayName("등록 이메일로 읽기 성공")
    public void testReadUserByTypeAndEmail() {
        // Given
        when(userRepository.readByTypeAndEmail(any(User.class))).thenReturn(Optional.of(sampleUser));

        // When
        User result = userReader.readUserByTypeAndEmail(sampleUser);

        // Then
        assertEquals(sampleUser, result);
    }

    @Test
    @DisplayName("이메일로 읽기 실패")
    public void testReadUserByEmail_UserNotFound() {
        // Given
        String userEmail = "test@example.com";
        when(userRepository.readByEmail(userEmail)).thenReturn(Optional.empty());

        // When / Then
        BusinessException exception = assertThrows(BusinessException.class, () -> userReader.readUserByEmail(userEmail));
        assertEquals(ErrorCode.USER_EMAIL_NOT_FOUND, exception.getErrorCode());
    }

    @Test
    @DisplayName("이메일로 읽기 성공")
    public void testReadUserByEmail() {
        // Given
        String userEmail = "test@example.com";
        when(userRepository.readByEmail(userEmail)).thenReturn(Optional.of(sampleUser));

        // When
        User result = userReader.readUserByEmail(userEmail);

        // Then
        assertEquals(sampleUser, result);
    }
}