package org.v1.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.v1.model.content.Contents;
import org.v1.model.content.GatheringArticle;
import org.v1.model.content.PhotoArticle;
import org.v1.model.like.Like;
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
    public Consumer<Message<byte[]>> photoContent() {
        return message -> {
            try {
                byte[] payload = message.getPayload();
                MessageHeaders headers = message.getHeaders();
                String headerValue = (String) headers.get("headerKey");
                if (Objects.requireNonNull(headerValue).equals("photo")) {
                    PhotoArticleMessageList photoArticleMessageList = objectMapper.readValue(payload, PhotoArticleMessageList.class);
                    List<PhotoArticle> popularCommunityArticle = photoArticleMessageList.popularCommunityArticleMessages()
                            .parallelStream()
                            .map(PhotoArticleMessage::toArticle)
                            .toList();
                    List<PhotoArticle> notPopularCommunityArticle = photoArticleMessageList.notPopularCommunityArticleMessages()
                            .parallelStream()
                            .map(PhotoArticleMessage::toArticle)
                            .toList();
                    log.info("Popular Community Article: {}", popularCommunityArticle);
                    communityService.updatePhotoArticle(new Contents.PhotoArticles(popularCommunityArticle, notPopularCommunityArticle));
                }
                if (Objects.requireNonNull(headerValue).equals("gathering")) {
                    GatheringArticleMessageList gatheringArticleMessageList = objectMapper.readValue(payload, GatheringArticleMessageList.class);
                    List<GatheringArticle> popularCommunityArticle = gatheringArticleMessageList.notPopularCommunityArticleMessages()
                            .parallelStream()
                            .map(GatheringArticleMessage::toArticle)
                            .toList();
                    List<GatheringArticle> notPopularCommunityArticle = gatheringArticleMessageList.notPopularCommunityArticleMessages()
                            .parallelStream()
                            .map(GatheringArticleMessage::toArticle)
                            .toList();
                    log.info("Popular Community Article: {}", popularCommunityArticle);
                    communityService.updateGatheringArticle(new Contents.GatheringArticles(popularCommunityArticle, notPopularCommunityArticle));
                }
                if (Objects.requireNonNull(headerValue).equals("like")) {
                    LikeMessage likeMessage = objectMapper.readValue(payload, LikeMessage.class);
                    communityService.likeCommunity(new Like(likeMessage.communityId()));
                    log.info("Popular Community Article: {}", likeMessage);
                }
            } catch (IOException e) {
                log.error("Error processing received message.", e);
            }
        };
    }
}
