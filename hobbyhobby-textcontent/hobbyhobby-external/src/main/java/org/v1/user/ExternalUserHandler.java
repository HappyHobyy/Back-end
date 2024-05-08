package org.v1.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.v1.service.UserService;

@Component
@AllArgsConstructor
@Slf4j
public class ExternalUserHandler {
    private final ObjectMapper objectMapper;
    private final UserService userService;
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "textcontent"),
                    exchange = @Exchange(value = "amq.direct", type = "direct"),
                    key = "textContent.userCreate"
            )
    )
    public void receiveUserCreate(byte[] payload) {
        try {
            UserMessage.CreateUserMessage message = objectMapper.readValue(payload, UserMessage.CreateUserMessage.class);
            log.info("Received message: {}", message);
            userService.appendUser(message.toUser());
        } catch (Exception e) {
            log.error("Error processing received message.", e);
        }
    }
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "textcontent"),
                    exchange = @Exchange(value = "amq.direct", type = "direct"),
                    key = "textContent.userCreate"
            )
    )
    public void receiveUserDelete(byte[] payload) {
        try {
            UserMessage.DeleteUserMessage message = objectMapper.readValue(payload, UserMessage.DeleteUserMessage.class);
            log.info("Received message: {}", message);
            userService.removeUser(message.userId());
        } catch (Exception e) {
            log.error("Error processing received message.", e);
        }
    }
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "textcontent"),
                    exchange = @Exchange(value = "amq.direct", type = "direct"),
                    key = "textContent.userCreate"
            )
    )
    public void receiveUserUpdate(byte[] payload) {
        try {
            UserMessage.UpdateUserMessage message = objectMapper.readValue(payload, UserMessage.UpdateUserMessage.class);
            log.info("Received message: {}", message);
            userService.updateUser(message.toUser());
        } catch (Exception e) {
            log.error("Error processing received message.", e);
        }
    }
}
