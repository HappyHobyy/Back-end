package org.v1.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.v1.error.BusinessException;
import org.v1.error.ErrorCode;
import org.v1.handler.ExternalCommunitySender;
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
    private final ExternalCommunitySender externalCommunitySender;
    @Transactional
    public void appendUser(
            final User user
    ){
        User savedUser = userRepository.appendUser(user)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_CREATE_FAILED));
        externalPhotoContentSender.sendUserCreate(savedUser);
        externalTextContentSender.sendUserCreate(savedUser);
        externalCommunitySender.sendUserCreate(savedUser);
    }
}
