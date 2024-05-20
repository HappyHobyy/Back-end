package org.v1.implementaion.like;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.v1.model.article.ArticleType;
import org.v1.model.like.LikeEvent;


@Component
public class LikeEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public LikeEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishLikeEvent(Integer communityId, ArticleType type) {
        LikeEvent likeEvent = new LikeEvent(this,communityId, LikeEvent.LikeType.LIKE,type);
        eventPublisher.publishEvent(likeEvent);
    }
    public void publishDisLikeEvent(Integer communityId, ArticleType type) {
        LikeEvent likeEvent = new LikeEvent(this,communityId, LikeEvent.LikeType.DISLIKE,type);
        eventPublisher.publishEvent(likeEvent);
    }
}