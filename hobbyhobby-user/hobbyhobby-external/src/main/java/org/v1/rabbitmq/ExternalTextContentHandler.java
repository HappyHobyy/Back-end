package org.v1.rabbitmq;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.stereotype.Component;
import org.v1.handler.TextContentHandler;
import org.v1.model.User;

import java.util.UUID;

@Component
@AllArgsConstructor
public class ExternalTextContentHandler implements TextContentHandler {
    private final MessageQueueSender messageQueueSender;

    @Override
    public void sendUserUpdate(User user) {
        UserMessage.UpdateUserMessage userRequestDto = UserMessage.UpdateUserMessage.of(user);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        messageQueueSender.sendMessage("textContent.userUpdate", userRequestDto, correlationData);
    }
    @Override
    public void sendUserDelete(Long userId) {
        UserMessage.DeleteUserMessage userRequestDto = UserMessage.DeleteUserMessage.of(userId);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        messageQueueSender.sendMessage("textContent.userDelete", userRequestDto, correlationData);
    }
    @Override
    public void sendUserCreate(User user) {
        UserMessage.CreateUserMessage userRequestDto = UserMessage.CreateUserMessage.of(user);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        messageQueueSender.sendMessage("textContent.userCreate", userRequestDto, correlationData);
    }
}