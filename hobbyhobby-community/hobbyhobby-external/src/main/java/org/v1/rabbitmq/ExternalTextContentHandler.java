package org.v1.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
@AllArgsConstructor
@Slf4j
public class ExternalTextContentHandler {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    public CompletableFuture<MessageDto> sendMessage(MessageDto messageDto) {
        CompletableFuture<MessageDto> future = new CompletableFuture<>();
        try {
            CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
            byte[] bytes = objectMapper.writeValueAsBytes(messageDto);
            rabbitTemplate.convertAndSend("user", "userRequest", bytes, correlationData);
            rabbitTemplate.setReplyTimeout(5000);
            rabbitTemplate.setCorrelationKey(correlationData.getId());
            Message responseMessage = rabbitTemplate.receive("userResponse", 5000);
            if (responseMessage == null) {
                log.error("Timeout while waiting for response.");
                future.completeExceptionally(new RuntimeException("Timeout while waiting for response."));
            } else {
                byte[] body = responseMessage.getBody();
                MessageDto responseDto = objectMapper.readValue(body, MessageDto.class);
                future.complete(responseDto);
            }
        } catch (JsonProcessingException jpe) {
            log.error("Parsing error occurred.", jpe);
            future.completeExceptionally(jpe);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return future;
    }
}
