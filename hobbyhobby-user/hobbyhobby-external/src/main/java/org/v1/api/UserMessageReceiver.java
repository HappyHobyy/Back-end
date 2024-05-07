package org.v1.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class UserMessageReceiver {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public UserMessageReceiver(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "userRequest", durable = "true"),
            exchange = @Exchange(value = "user", type = "direct")
    ))
    public void receiveUserRequest(byte[] payload) {
        try {
            MessageDto messageDto = objectMapper.readValue(payload, MessageDto.class);
            // 역직렬화된 MessageDto 객체 사용
            log.info("Received message: {}", messageDto);
            byte[] bytes = objectMapper.writeValueAsBytes(messageDto);
            rabbitTemplate.convertAndSend("user", "userResponse", bytes);
        } catch (Exception e) {
            log.error("Error processing received message.", e);
        }
    }
}
