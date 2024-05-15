package org.v1.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.v1.rabbitmq.dto.UserMessage;
import org.v1.service.user.UserService;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;

@Component
@AllArgsConstructor
@Slf4j
public class ExternalUserMessageReceiver {
    private final ObjectMapper objectMapper;
    private final UserService userService;
    @Bean
    public Consumer<Message<byte[]>> user() {
        return message -> {
            try {
                byte[] payload = message.getPayload();
                MessageHeaders headers = message.getHeaders();
                String headerValue = (String) headers.get("headerKey");
                switch (Objects.requireNonNull(headerValue)) {
                    case "create":
                        UserMessage.CreateUserMessage createUserMessage = objectMapper.readValue(payload, UserMessage.CreateUserMessage.class);
                        log.info("Received message: {}", createUserMessage);
                        userService.appendUser(createUserMessage.toUser());
                        break;
                    case "delete":
                        UserMessage.DeleteUserMessage deleteUserMessage = objectMapper.readValue(payload, UserMessage.DeleteUserMessage.class);
                        log.info("Received message: {}", deleteUserMessage);
                        userService.removeUser(deleteUserMessage.userId());
                        break;
                    case "update":
                        UserMessage.UpdateUserMessage updateUserMessage = objectMapper.readValue(payload, UserMessage.UpdateUserMessage.class);
                        log.info("Received message: {}", updateUserMessage);
                        userService.updateUser(updateUserMessage.toUser());
                        break;
                    default:
                        log.warn("Unhandled routing key: {}", payload);
                        break;
                }
            } catch (IOException e) {
                log.error("Error processing received message.", e);
            }
        };
    }
}
