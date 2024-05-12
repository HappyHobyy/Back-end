package org.v1.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.v1.model.content.Contents;
import org.v1.model.content.PhotoArticle;
import org.v1.service.CommunityService;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

@Component
@AllArgsConstructor
@Slf4j
public class ExternalPhotoContentMessageReceiver {
    private final ObjectMapper objectMapper;
    private final CommunityService communityService;
    @Bean
    public Consumer<Message<byte[]>> user() {
        return message -> {
            try {
                byte[] payload = message.getPayload();
                MessageHeaders headers = message.getHeaders();
                String headerValue = (String) headers.get("headerKey");
                if (Objects.requireNonNull(headerValue).equals("photo")) {
                    PhotoArticleMessageList photoArticleMessageList = objectMapper.readValue(payload,PhotoArticleMessageList.class);
                    List<PhotoArticle> popularCommunityArticle = photoArticleMessageList.notPopularCommunityArticleMessages()
                            .parallelStream()
                            .map(PhotoArticleMessage::toArticle)
                            .toList();
                    List<PhotoArticle> notPopularCommunityArticle = photoArticleMessageList.notPopularCommunityArticleMessages()
                            .parallelStream()
                            .map(PhotoArticleMessage::toArticle)
                            .toList();
                    communityService.updatePhotoArticle(new Contents.PhotoArticles(popularCommunityArticle,notPopularCommunityArticle));
                } else {
                    log.warn("Unhandled routing key: {}", payload);
                }
            } catch (IOException e) {
                log.error("Error processing received message.", e);
            }
        };
    }
}
