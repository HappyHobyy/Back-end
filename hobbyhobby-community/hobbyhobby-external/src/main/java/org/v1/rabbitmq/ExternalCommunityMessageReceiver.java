package org.v1.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.v1.service.CommunityService;

import java.util.Objects;
import java.util.function.Consumer;

@Component
@AllArgsConstructor
@Slf4j
public class ExternalCommunityMessageReceiver {
    private final CommunityService communityService;

    @Bean
    public Consumer<Message<Objects>> allCommunity() {
        return message -> {
            MessageHeaders headers = message.getHeaders();
            String headerValue = (String) headers.get("headerKey");
            switch (Objects.requireNonNull(headerValue)) {
                case "photo":
                    communityService.clearCachedPhotoArticles();
                    break;
                case "group":
                    log.info("Received message: {group}");
                    break;
                default:
                    log.warn("Unhandled routing key: {}", message);
                    break;
            }
        };
    }
}
