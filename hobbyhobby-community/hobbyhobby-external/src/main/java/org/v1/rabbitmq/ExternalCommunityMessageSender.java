package org.v1.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExternalCommunityMessageSender {
    @Autowired
    private StreamBridge streamBridge;
    private final ObjectMapper objectMapper;

    public void sendAllCommunity(String header) {
        Object payload = new Object();
        Message<Object> msg = MessageBuilder
                .withPayload(payload)
                .setHeader("headerKey", header)
                .build();
        streamBridge.send("allCommunity-out-0", msg);
    }
}
