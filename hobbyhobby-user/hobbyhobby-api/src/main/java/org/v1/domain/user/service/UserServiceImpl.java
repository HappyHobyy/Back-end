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
    private final UserReader userReader;
    private final UserRemover userRemover;
    @Override
    @Transactional
    public void removeUser(Long userId){
        User savedUser = userReader.readById(userId);
        userRemover.remove(savedUser.getId());
    }
    @Override
    public User getMyPage(final Long userId) {
        return userReader.readById(userId);
    }
}
