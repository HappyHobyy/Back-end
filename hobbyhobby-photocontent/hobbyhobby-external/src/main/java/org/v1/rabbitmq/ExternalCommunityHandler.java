package org.v1.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.v1.external.ExternalCommunitySender;
import org.v1.model.photoartlcle.PhotoArticle;
import org.v1.rabbitmq.dto.PhotoArticleMessage;
import org.v1.rabbitmq.dto.PhotoArticleMessageList;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class ExternalCommunityHandler implements ExternalCommunitySender {
    private final ExternalCommunityMessageSender messageHandler;

    @Override
    public void sendCommunityPopularArticle(List<PhotoArticle> popularCommunity, List<PhotoArticle> notPopularCommunity) {
        messageHandler.sendCommunityFromPhotoContent(new PhotoArticleMessageList(PhotoArticleMessage.of(popularCommunity),PhotoArticleMessage.of(notPopularCommunity)),"photo");
    }
}
