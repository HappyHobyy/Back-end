package org.v1.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.v1.handler.TextContentHandler;
import org.v1.model.User;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class ExternalTextContentHandler implements TextContentHandler {
    private final MessageQueueSender messageQueueSender;

    @Override
    public void sendUserUpdate(User user) {
        UserMessageRequest.UpdateUserMessage userRequestDto = UserMessageRequest.UpdateUserMessage.of(user);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        messageQueueSender.sendMessage("textContent.userUpdate", userRequestDto, correlationData);
    }
    @Override
    public void sendUserDelete(Long userId) {
        UserMessageRequest.DeleteUserMessage userRequestDto = UserMessageRequest.DeleteUserMessage.of(userId);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        messageQueueSender.sendMessage("textContent.userDelete", userRequestDto, correlationData);
    }
    @Override
    public void sendUserCreate(User user) {
        UserMessageRequest.CreateUserMessage userRequestDto = UserMessageRequest.CreateUserMessage.of(user);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        messageQueueSender.sendMessage("textContent.userCreate", userRequestDto, correlationData);
    }
}