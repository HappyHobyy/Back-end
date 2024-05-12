package org.v1.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;

@Component
@AllArgsConstructor
@Slf4j
public class ExternalUserMessageReceiver {
    private final ObjectMapper objectMapper;

    @Bean
    public Consumer<Message<byte[]>> photoContent() {
        return message -> {
            byte[] payload = message.getPayload();
            MessageHeaders headers = message.getHeaders();
            String headerValue = (String) headers.get("headerKey");
            log.info("Received message: {}", payload);
            log.info("Header value: {}", headerValue);
        };
    }
}
