package org.v1.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.v1.domain.user.domain.User;
import org.v1.domain.user.implementation.*;

import org.v1.error.BusinessException;
import org.v1.error.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageWriter;
import java.io.File;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserReader userReader;
    private final UserRemover userRemover;
    private final UserAppender userAppender;
    private final ImageAppender imageAppender;
    private final ImageRemover imageRemover;
    @Transactional
    public void removeUser(Long userId){
        User savedUser = userReader.readById(userId);
        userRemover.remove(savedUser.getId().value());
    }
    public User getMyPage(final Long userId) {
        return userReader.readById(userId);
    }
    public void changeProfileImage(final File file, final Long userId) {
        User user = userReader.readById(userId);
        if (user.getImageUrl() != null){
            imageRemover.removeImage(user.getImageUrl());
        }
        userAppender.updateUser(User.withId(
                user.getId(),
                user.getNickname(),
                user.getEmail(),
                user.getUserType(),
                user.getPassword(),
                user.getUserRole(),
                user.getUserGender(),
                user.getNationality(),
                user.getDeviceToken(),
                imageAppender.appendImage(file, userId.toString()
                )
        ));
    }
}
