package org.v1.implementaion.like;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.v1.model.like.LikeEvent;


@Component
public class LikeEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public LikeEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishLikeEvent(Integer communityId) {
        LikeEvent likeEvent = new LikeEvent(this,communityId, LikeEvent.LikeType.LIKE);
        applicationEventPublisher.publishEvent(likeEvent);
    }
    public void publishDisLikeEvent(Integer communityId) {
        LikeEvent likeEvent = new LikeEvent(this,communityId, LikeEvent.LikeType.DISLIKE);
        applicationEventPublisher.publishEvent(likeEvent);
    }
}