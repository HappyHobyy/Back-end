package org.v1.service;

import lombok.RequiredArgsConstructor;
import org.v1.handler.PhotoContentHandler;
import org.v1.handler.TextContentHandler;
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
    private final UserAppender userAppender;
    private final ImageAppender imageAppender;
    private final ImageRemover imageRemover;
    private final PhotoContentHandler photoContentHandler;
    private final TextContentHandler textContentHandler;
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
        User updatedUser = User.withId(
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
                ));
        userAppender.updateUser(updatedUser);
        photoContentHandler.sendUserUpdate(updatedUser);
        textContentHandler.sendUserUpdate(updatedUser);
    }
}
