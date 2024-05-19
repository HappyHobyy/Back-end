package org.v1.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.handler.ExternalPhotoContentSender;
import org.v1.handler.ExternalTextContentSender;
import org.v1.model.User;
import org.v1.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class UserUpdater {
    private final UserRepository userRepository;
    private final ExternalPhotoContentSender externalPhotoContentSender;
    private final ExternalTextContentSender externalTextContentSender;
    public void updateUser(final User user) {
        userRepository.update(user);
        externalPhotoContentSender.sendUserUpdate(user);
        externalTextContentSender.sendUserUpdate(user);
    }
}
