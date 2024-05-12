package org.v1.model;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class LikeEvent extends ApplicationEvent {
    private final Integer communityId;
    private final LikeType likeType;

    public LikeEvent(Object source,Integer communityId,LikeType likeType) {
        super(source);
        this.communityId =communityId;
        this.likeType = likeType;
    }

    public enum LikeType {
        LIKE,
        DISLIKE
    }
}
