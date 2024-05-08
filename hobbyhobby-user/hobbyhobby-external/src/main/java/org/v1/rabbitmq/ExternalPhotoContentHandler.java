package org.v1.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.stereotype.Component;
import org.v1.handler.PhotoContentHandler;
import org.v1.model.User;

import java.util.UUID;

@Component
@AllArgsConstructor
@Slf4j
public class ExternalPhotoContentHandler implements PhotoContentHandler {
    private final MessageQueueSender messageQueueSender;

    @Override
    public void sendUserUpdate(User user) {
        UserMessage.UpdateUserMessage userRequestDto = UserMessage.UpdateUserMessage.of(user);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        messageQueueSender.sendMessage("photoContent.userUpdate", userRequestDto, correlationData);
    }
    @Override
    public void sendUserDelete(Long userId) {
        UserMessage.DeleteUserMessage userRequestDto = UserMessage.DeleteUserMessage.of(userId);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        messageQueueSender.sendMessage("photoContent.userDelete", userRequestDto, correlationData);
    }
    @Override
    public void sendUserCreate(User user) {
        UserMessage.CreateUserMessage userRequestDto = UserMessage.CreateUserMessage.of(user);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        messageQueueSender.sendMessage("photoContent.userCreate", userRequestDto, correlationData);
    }
}
