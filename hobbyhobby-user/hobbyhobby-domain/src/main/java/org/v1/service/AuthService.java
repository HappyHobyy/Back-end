package org.v1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.v1.handler.PhotoContentHandler;
import org.v1.handler.TextContentHandler;
import org.v1.model.User;
import org.v1.implementation.UserAppender;
import org.v1.implementation.UserChecker;
import org.v1.implementation.UserReader;
import org.v1.error.BusinessException;
import org.v1.error.ErrorCode;

@Service
@RequiredArgsConstructor
public class
AuthService {
    private final UserAppender userAppender;
    private final UserReader userReader;
    private final UserChecker userChecker;
    private final PhotoContentHandler photoContentHandler;
    private final TextContentHandler textContentHandler;

    public void registerDefaultUser(User user) {
        if (userChecker.isUserEmailDuplicate(user)) {
            throw new BusinessException(ErrorCode.USER_EMAIL_DUPLICATED);
        }
        User hashedUser = User.withoutId(
                user.getNickname(),
                user.getEmail(),
                user.getUserType(),
                user.getPassword().hashPassword(),
                user.getUserRole(),
                user.getUserGender(),
                user.getNationality(),
                null,
                null
        );
        User savedUser = userAppender.appendUser(hashedUser);
        photoContentHandler.sendUserCreate(savedUser);
        textContentHandler.sendUserCreate(savedUser);
    }
    public void registerOAuthUser(User user) {
        if (userChecker.isUserEmailDuplicate(user)) {
            throw new BusinessException(ErrorCode.USER_EMAIL_DUPLICATED);
        }
        User savedUser = userAppender.appendUser(user);
        photoContentHandler.sendUserCreate(savedUser);
        textContentHandler.sendUserCreate(savedUser);
    }
    @Transactional
    public User loginDefaultUser(User user) {
        User savedUser = userReader.readUserByTypeAndEmail(user);
        if (!savedUser.getPassword().matches(user.getPassword().hashPassword())) {
            throw new BusinessException(ErrorCode.USER_LOGIN_PASSWORD_FAIL);
        }
        User updateUser = User.withId(
                savedUser.getId(),
                savedUser.getNickname(),
                savedUser.getEmail(),
                savedUser.getUserType(),
                savedUser.getPassword(),
                savedUser.getUserRole(),
                savedUser.getUserGender(),
                savedUser.getNationality(),
                user.getDeviceToken(),
                savedUser.getImageUrl()
        );
        userAppender.updateUser(updateUser);
        return updateUser;
    }
    @Transactional
    public User loginOAuthUser(User user) {
        User savedUser = userReader.readUserByTypeAndEmail(user);
        User updateUser = User.withId(
                savedUser.getId(),
                savedUser.getNickname(),
                savedUser.getEmail(),
                savedUser.getUserType(),
                null,
                savedUser.getUserRole(),
                savedUser.getUserGender(),
                savedUser.getNationality(),
                user.getDeviceToken(),
                savedUser.getImageUrl()
        );
        userAppender.updateUser(updateUser);
        return updateUser;
    }
    public void checkEmailDuplicate(User user) {
        if (userChecker.isUserEmailDuplicate(user)) {
            throw new BusinessException(ErrorCode.USER_EMAIL_DUPLICATED);
        }
    }
    public void checkNicknameDuplicate(User user) {
        if (userChecker.isUserNicknameDuplicate(user)) {
            throw new BusinessException(ErrorCode.USER_NICKNAME_DUPLICATED);
        }
    }
}
