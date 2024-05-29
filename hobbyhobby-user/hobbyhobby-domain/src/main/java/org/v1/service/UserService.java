package org.v1.service;

import lombok.RequiredArgsConstructor;

import org.v1.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.v1.implementation.*;

import java.io.File;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserReader userReader;
    private final UserRemover userRemover;
    private final UserUpdater userUpdater;
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
        String newImageUrl = imageAppender.appendImage(file, userId.toString());
        User updatedUser = user.updateUserImage(newImageUrl);
        userUpdater.updateUser(updatedUser);
    }
}
