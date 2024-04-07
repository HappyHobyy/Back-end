package org.v1.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.v1.domain.user.domain.User;
import org.v1.domain.user.implementation.UserAppender;
import org.v1.domain.user.implementation.UserChecker;
import org.v1.domain.user.implementation.UserReader;
import org.v1.domain.user.implementation.UserRemover;

import org.v1.error.BusinessException;
import org.v1.error.ErrorCode;
import org.v1.global.config.security.password.PasswordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserAppender userAppender;
    private final UserReader userReader;
    private final UserChecker userChecker;
    private final UserRemover userRemover;
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
    @Override
    @Transactional
    public void removeUser(Long userId){
        User savedUser = userReader.readById(userId);
        userRemover.remove(savedUser.getId());
    }

    public User getMyPage(final Long userId) {
        return userReader.readById(userId);
    }
}
