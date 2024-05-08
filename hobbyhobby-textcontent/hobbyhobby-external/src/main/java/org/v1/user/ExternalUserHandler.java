package org.v1.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.v1.service.UserService;

import java.io.IOException;

@Component
@AllArgsConstructor
@Slf4j
public class ExternalUserHandler {
    private final ObjectMapper objectMapper;
    private final UserService userService;


    @RabbitListener(queues = "textcontent")
    public void receiveMessage(byte[] payload, @Header(AmqpHeaders.RECEIVED_ROUTING_KEY) String routingKey) {
        try {
            switch (routingKey) {
                case "textContent.userCreate":
                    UserMessage.CreateUserMessage createUserMessage = objectMapper.readValue(payload, UserMessage.CreateUserMessage.class);
                    log.info("Received message: {}", createUserMessage);
                    userService.appendUser(createUserMessage.toUser());
                    break;
                case "textContent.userDelete":
                    UserMessage.DeleteUserMessage deleteUserMessage = objectMapper.readValue(payload, UserMessage.DeleteUserMessage.class);
                    log.info("Received message: {}", deleteUserMessage);
                    userService.removeUser(deleteUserMessage.userId());
                    break;
                case "textContent.userUpdate":
                    UserMessage.UpdateUserMessage updateUserMessage = objectMapper.readValue(payload, UserMessage.UpdateUserMessage.class);
                    log.info("Received message: {}", updateUserMessage);
                    userService.updateUser(updateUserMessage.toUser());
                    break;
                default:
                    log.warn("Unhandled routing key: {}", routingKey);
                    break;
            }
        } catch (IOException e) {
            log.error("Error processing received message.", e);
        }
    }
}
