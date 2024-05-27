package org.v1.implementaion.like;

import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.v1.implementaion.community.CommunityManager;
import org.v1.model.like.LikeEvent;

@Component
@AllArgsConstructor
public class LikeEventListener {

    private final CommunityManager manager;


    @EventListener
    public void handleLikeEvent(LikeEvent likeEvent) {
        Integer communityId = likeEvent.getCommunityId();
        if (likeEvent.getLikeType() == LikeEvent.LikeType.LIKE) {
            manager.plusCommunityLikes(communityId,likeEvent.getType());
        } else {
            manager.minusCommunityLikes(communityId,likeEvent.getType());
        }
    }
}