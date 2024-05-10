package org.v1.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExternalMessageHandler {
    @Autowired
    private StreamBridge streamBridge;
    private final ObjectMapper objectMapper;

    public void sendTextContentFromUser(@NotNull Object message,String header) {
        try {
            byte[] payload = objectMapper.writeValueAsBytes(message);
            Message<byte[]> msg = MessageBuilder
                    .withPayload(payload)
                    .setHeader("headerKey", header)
                    .build();
            streamBridge.send("userCached-out-0", msg);
        } catch (JsonProcessingException e) {
            log.error("Error serializing message payload: {}", e.getMessage());
        }
    }
    public void sendPhotoContentFromUser(@NotNull Object message,String header) {
        try {
            byte[] payload = objectMapper.writeValueAsBytes(message);
            Message<byte[]> msg = MessageBuilder
                    .withPayload(payload)
                    .setHeader("headerKey", header)
                    .build();
            streamBridge.send("user-out-1", msg);
        } catch (JsonProcessingException e) {
            log.error("Error serializing message payload: {}", e.getMessage());
        }
    }
    public void refreshUserCached(@NotNull Object message) {
        try {
            byte[] payload = objectMapper.writeValueAsBytes(message);
            Message<byte[]> msg = MessageBuilder
                    .withPayload(payload)
                    .build();
            streamBridge.send("userCached-out-0", msg);
        } catch (JsonProcessingException e) {
            log.error("Error serializing message payload: {}", e.getMessage());
        }
    }
    @Bean
    public Consumer<Message<byte[]>> userCached() {
        return message -> {
            byte[] payload = message.getPayload();
            MessageHeaders headers = message.getHeaders();
            String headerValue = (String) headers.get("headerKey");
            log.info("UserCachedRefresh: {}", payload);
            log.info("Header value: {}", headerValue);
        };
    }
}