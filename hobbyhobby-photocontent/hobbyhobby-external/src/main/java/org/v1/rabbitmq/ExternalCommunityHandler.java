package org.v1.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.v1.external.ExternalCommunitySender;
import org.v1.model.article.GatheringArticle;
import org.v1.model.article.PhotoArticle;
import org.v1.rabbitmq.dto.GatheringArticleMessage;
import org.v1.rabbitmq.dto.GatheringArticleMessageList;
import org.v1.rabbitmq.dto.PhotoArticleMessage;
import org.v1.rabbitmq.dto.PhotoArticleMessageList;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class ExternalCommunityHandler implements ExternalCommunitySender {
    private final ExternalCommunityMessageSender messageHandler;

    @Override
    public void sendPopularPhotoArticle(List<PhotoArticle> popularCommunity, List<PhotoArticle> notPopularCommunity) {
        messageHandler.sendCommunityFromPhotoContent(new PhotoArticleMessageList(PhotoArticleMessage.of(popularCommunity),PhotoArticleMessage.of(notPopularCommunity)),"photo");
    }

    @Override
    public void sendPopularGatheringArticle(List<GatheringArticle> popularCommunity, List<GatheringArticle> notPopularCommunity) {
        messageHandler.sendCommunityFromPhotoContent(new GatheringArticleMessageList(GatheringArticleMessage.of(popularCommunity),GatheringArticleMessage.of(notPopularCommunity)),"gathering");
    }
}
