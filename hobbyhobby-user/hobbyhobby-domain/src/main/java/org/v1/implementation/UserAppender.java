package org.v1.implementation;

import lombok.RequiredArgsConstructor;
import org.v1.handler.ExternalPhotoContentSender;
import org.v1.handler.ExternalTextContentSender;
import org.v1.model.User;
import org.v1.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAppender {
    private final UserRepository userRepository;
    private final ExternalPhotoContentSender externalPhotoContentSender;
    private final ExternalTextContentSender externalTextContentSender;
    public User appendUser(
            final User user
    ){
        User savedUser = userRepository.appendUser(user).orElseThrow();
        externalPhotoContentSender.sendUserCreate(savedUser);
        externalTextContentSender.sendUserCreate(savedUser);
        return savedUser;
    }
}
