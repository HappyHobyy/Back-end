package org.v1.model;

import org.springframework.context.ApplicationEvent;

public class LikeEvent extends ApplicationEvent {
    private final Integer communityId;
    private final LikeType likeType;

    public LikeEvent(Object source,Integer communityId,LikeType likeType) {
        super(source);
        this.communityId =communityId;
        this.likeType = likeType;
    }
    public Integer getCommunityId() {
        return communityId;
    }
    public LikeType getLikeType() {
        return likeType;
    }
    public enum LikeType {
        LIKE,
        DISLIKE
    }
}
