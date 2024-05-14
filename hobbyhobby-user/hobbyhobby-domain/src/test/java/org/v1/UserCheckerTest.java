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
import org.v1.implementation.UserAppender;
import org.v1.implementation.UserChecker;
import org.v1.model.User;
import org.v1.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class UserCheckerTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserChecker userChecker;
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
    @DisplayName("이메일이 중복된다")
    public void UserEmailDuplicated() {
        //Given
        when(userRepository.checkByEmail(any(User.class))).thenReturn(true);
        BusinessException exception = assertThrows(BusinessException.class,
                () -> userChecker.isUserEmailDuplicate(sampleUser));
        assertEquals(ErrorCode.USER_EMAIL_DUPLICATED, exception.getErrorCode());
    }
    @Test
    @DisplayName("닉네임이 중복된다")
    public void UserNickNameDuplicated() {
        //Given
        when(userRepository.checkByNickname(any(User.class))).thenReturn(true);
        BusinessException exception = assertThrows(BusinessException.class,
                () -> userChecker.isUserNicknameDuplicate(sampleUser));
        assertEquals(ErrorCode.USER_NICKNAME_DUPLICATED, exception.getErrorCode());
    }
}
