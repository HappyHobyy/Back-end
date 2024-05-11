package org.v1.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.v1.handler.PhotoContentHandler;
import org.v1.model.User;

@Component
@AllArgsConstructor
@Slf4j
public class ExternalPhotoContentHandler implements PhotoContentHandler {
    private final ExternalMessageHandler messageHandler;

    @Override
    public void sendUserUpdate(User user) {
        UserMessage.UpdateUserMessage userRequestDto = UserMessage.UpdateUserMessage.of(user);
        messageHandler.sendPhotoContentFromUser(userRequestDto,"update");
    }
    @Override
    public void sendUserDelete(Long userId) {
        UserMessage.DeleteUserMessage userRequestDto = UserMessage.DeleteUserMessage.of(userId);
        messageHandler.sendPhotoContentFromUser(userRequestDto,"delete");
    }
    @Override
    public void sendUserCreate(User user) {
        UserMessage.CreateUserMessage userRequestDto = UserMessage.CreateUserMessage.of(user);
        messageHandler.sendPhotoContentFromUser(userRequestDto,"create");
    }
}
