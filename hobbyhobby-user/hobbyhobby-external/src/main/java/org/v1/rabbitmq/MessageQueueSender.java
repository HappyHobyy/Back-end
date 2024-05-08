package org.v1.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
@Slf4j
public class MessageQueueSender {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    public void sendMessage(String routingKey, Object messageDto, CorrelationData correlationData) {
        byte[] bytes;
        try {
            bytes = objectMapper.writeValueAsBytes(messageDto);
        } catch (JsonProcessingException e) {
            log.error("Failed to convert message to bytes.", e);
            return;
        }
        int maxAttempts = 3;
        int attempt = 0;
        while (attempt < maxAttempts) {
            try {
                log.info(routingKey, new String(bytes),"sending message");
                rabbitTemplate.convertAndSend("amq.direct", routingKey, bytes, correlationData);
                break;
            } catch (AmqpException e) {
                log.warn("Failed to send message (attempt {}/{}): {}", attempt + 1, maxAttempts, e.getMessage());
                attempt++;
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException interruptedException) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        if (attempt == maxAttempts) {
            log.error("Failed to send message after {} attempts.", maxAttempts);
        }
    }
}
