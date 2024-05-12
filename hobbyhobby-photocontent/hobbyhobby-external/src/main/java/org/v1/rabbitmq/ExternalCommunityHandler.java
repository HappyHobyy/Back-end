package org.v1.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.v1.external.ExternalCommunitySender;
import org.v1.model.PhotoArticle;
import org.v1.rabbitmq.dto.PhotoArticleMessage;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class ExternalCommunityHandler implements ExternalCommunitySender {
    private final ExternalCommunityMessageSender messageHandler;

    @Override
    public void sendPopularCommunityArticle(List<PhotoArticle> photos) {
        messageHandler.sendCommunityFromPhotoContent(PhotoArticleMessage.of(photos),"Popular");
    }

    @Override
    public void sendNotPopularCommunityArticle(List<PhotoArticle> photos) {
        messageHandler.sendCommunityFromPhotoContent(PhotoArticleMessage.of(photos),"NotPopular");
    }
}
