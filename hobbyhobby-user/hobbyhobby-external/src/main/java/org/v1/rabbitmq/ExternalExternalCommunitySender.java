package org.v1.rabbitmq;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.handler.ExternalCommunitySender;
import org.v1.model.User;

@Component
@AllArgsConstructor
public class ExternalExternalCommunitySender implements ExternalCommunitySender {
    private final ExternalMessageHandler messageHandler;

    @Override
    public void sendUserUpdate(User user) {
        UserMessage.UpdateUserMessage userRequestDto = UserMessage.UpdateUserMessage.of(user);
        messageHandler.sendCommunityFromUser(userRequestDto,"update");
    }
    @Override
    public void sendUserDelete(Long userId) {
        UserMessage.DeleteUserMessage userRequestDto = UserMessage.DeleteUserMessage.of(userId);
        messageHandler.sendCommunityFromUser(userRequestDto,"delete");
    }
    @Override
    public void sendUserCreate(User user) {
        UserMessage.CreateUserMessage userRequestDto = UserMessage.CreateUserMessage.of(user);
        messageHandler.sendCommunityFromUser(userRequestDto,"create");
    }
}