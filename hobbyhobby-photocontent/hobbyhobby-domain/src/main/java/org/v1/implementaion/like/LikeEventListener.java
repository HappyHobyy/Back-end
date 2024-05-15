package org.v1.implementaion.like;

import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.v1.implementaion.community.CommunityManager;
import org.v1.model.like.LikeEvent;

@Component
@AllArgsConstructor
public class LikeEventListener {

    private final CommunityManager communityManager;


    @EventListener
    public void handleLikeEvent(LikeEvent likeEvent) {
        Integer communityId = likeEvent.getCommunityId();
        if (likeEvent.getLikeType() == LikeEvent.LikeType.LIKE) {
            communityManager.plusCommunityLikes(communityId);
        } else {
            communityManager.minusCommunityLikes(communityId);
        }
    }
}