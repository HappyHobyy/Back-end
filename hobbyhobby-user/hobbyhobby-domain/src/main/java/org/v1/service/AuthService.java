package org.v1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.v1.handler.ExternalPhotoContentSender;
import org.v1.handler.ExternalTextContentSender;
import org.v1.implementation.*;
import org.v1.model.User;
import org.v1.error.BusinessException;
import org.v1.error.ErrorCode;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class
AuthService {
    private final UserAppender userAppender;
    private final UserReader userReader;
    private final UserChecker userChecker;
    private final UserUpdater userUpdater;
    private final UserValidator userValidator;

    public void registerDefaultUser(User user) {
        userChecker.isUserEmailDuplicate(user);
        userAppender.appendUser(user.registDefaultUser());
    }
    public void registerOAuthUser(User oAuthUser) {
        userChecker.isUserEmailDuplicate(oAuthUser);
        userAppender.appendUser(oAuthUser);
    }
    @Transactional
    public User loginDefaultUser(User user) {
        User savedUser = userReader.readUserByEmail(user.getEmail());
        userValidator.validateUserType(user.getUserType(),savedUser);
        userValidator.validatePasswordCorrect(user,savedUser);
        User updateUser = savedUser.updateUserDeviceToken(user.getDeviceToken());
        userUpdater.updateUser(updateUser);
        return updateUser;
    }
    @Transactional
    public User loginOAuthUser(User user) {
        User savedUser = userReader.readUserByEmail(user.getEmail());
        userValidator.validateUserType(user.getUserType(),savedUser);
        User updateUser = savedUser.updateUserDeviceToken(user.getDeviceToken());
        userUpdater.updateUser(updateUser);
        return updateUser;
    }
    public void checkEmailDuplicate(User user) {
        userChecker.isUserEmailDuplicate(user);
    }
    public void checkNicknameDuplicate(User user) {
        userChecker.isUserNicknameDuplicate(user);
    }
}
