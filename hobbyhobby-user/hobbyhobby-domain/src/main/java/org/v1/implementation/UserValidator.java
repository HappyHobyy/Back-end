package org.v1.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.error.BusinessException;
import org.v1.error.ErrorCode;
import org.v1.model.User;

import java.util.Objects;

@Component
@AllArgsConstructor
public class UserValidator {
    public void validatePasswordCorrect(User user, User savedUser) {
        if (!savedUser.getPassword().matches(Objects.requireNonNull(user.getPassword().hashPassword()))) {
            throw new BusinessException(ErrorCode.USER_LOGIN_PASSWORD_FAIL);
        }
    }
    public void validateUserType(User.UserType userType, User savedUser) {
        if (userType != savedUser.getUserType()) {
            throw new BusinessException(ErrorCode.USER_TYPE_IS_NOT_VALIDATE);
        }
    }
}
