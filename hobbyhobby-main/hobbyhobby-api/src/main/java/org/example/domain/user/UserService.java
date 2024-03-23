package org.example.domain.user;

import lombok.RequiredArgsConstructor;
import org.example.domain.user.domain.User;
import org.example.domain.user.implemetion.UserAppender;
import org.example.domain.user.implemetion.UserChecker;
import org.example.domain.user.implemetion.UserReader;
import org.example.error.BusinessException;
import org.example.error.ErrorCode;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserAppender userAppender;
    private final UserChecker userChecker;
    private final UserReader userReader;

    public void defaultRegister(User user){
        if(!checkEmail(user.getEmail())) {
            userAppender.append(user);
        }
    }

    public User defaultLogin(User user){
        User savedUser = userReader.readByEmail(user);
        if (!savedUser.getPassword().equals(user.getPassword())) {
            throw new BusinessException(ErrorCode.USER_LOGIN_PASSWORD_FAIL);
        }
        return savedUser;
    }

    public boolean checkEmail(final String nickname) {
        return userChecker.checkUserEmailDuplication(nickname);
    }

}
