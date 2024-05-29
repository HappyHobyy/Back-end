package org.v1.implementation;

import lombok.RequiredArgsConstructor;
import org.v1.handler.ExternalCommunitySender;
import org.v1.handler.ExternalPhotoContentSender;
import org.v1.handler.ExternalTextContentSender;
import org.v1.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRemover {
    private final UserRepository userRepository;
    private final ExternalPhotoContentSender externalPhotoContentSender;
    private final ExternalTextContentSender externalTextContentSender;
    private final ExternalCommunitySender externalCommunitySender;
    public void remove(final Long userId) {
        userRepository.remove(userId);
        externalPhotoContentSender.sendUserDelete(userId);
        externalTextContentSender.sendUserDelete(userId);
        externalCommunitySender.sendUserDelete(userId);
    }
}
