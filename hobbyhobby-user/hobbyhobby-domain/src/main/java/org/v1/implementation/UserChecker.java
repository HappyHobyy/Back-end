package org.v1.implementation;

import lombok.RequiredArgsConstructor;
import org.v1.error.BusinessException;
import org.v1.error.ErrorCode;
import org.v1.model.User;
import org.v1.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserChecker {

    private final UserRepository userRepository;

    public void isUserEmailDuplicate(final User user) {
        if (userRepository.checkByEmail(user)) {
            throw new BusinessException(ErrorCode.USER_EMAIL_DUPLICATED);
        }
    }
    public void isUserNicknameDuplicate(final User user) {
        if (userRepository.checkByNickname(user)) {
            throw new BusinessException(ErrorCode.USER_NICKNAME_DUPLICATED);
        }
    }
}
