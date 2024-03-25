package org.example.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.user.domain.User;
import org.example.domain.user.implementation.UserAppender;
import org.example.domain.user.implementation.UserChecker;
import org.example.domain.user.implementation.UserReader;
import org.example.domain.user.service.UserService;
import org.example.error.BusinessException;
import org.example.error.ErrorCode;
import org.example.global.config.security.password.PasswordService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserAppender userAppender;
    private final UserReader userReader;
    private final UserChecker userChecker;
    private final PasswordService passwordService;

    @Override
    public void registerUser(User user) {
        if (userChecker.isUserEmailDuplicate(user)) {
            throw new BusinessException(ErrorCode.USER_REGISTER_EMAIL_FAIL);
        }
        user.hashedPassword(passwordService.encodePassword(user));
        userAppender.appendUser(user);
    }
    @Override
    public User loginUser(User user) {
        User savedUser = userReader.readUserByTypeAndEmail(user);
        if (!passwordService.matchPassword(user.getPassword(), savedUser.getPassword())) {
            throw new BusinessException(ErrorCode.USER_LOGIN_PASSWORD_FAIL);
        }
        return savedUser;
    }
    @Override
    public User loginUserWithKakao(User user) {
        if (!userChecker.isUserEmailDuplicate(user)) {
            userAppender.appendUser(user);
        }
        return userReader.readUserByTypeAndEmail(user);
    }

    @Override
    public User loginUserWithGoogle(User user) {
        if (!userChecker.isUserEmailDuplicate(user)) {
            userAppender.appendUser(user);
        }
        return userReader.readUserByTypeAndEmail(user);
    }
}
