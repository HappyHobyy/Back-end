package org.v1.model.like;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.v1.model.article.ArticleType;

@Getter
public class LikeEvent extends ApplicationEvent {
    private final Integer communityId;
    private final LikeType likeType;
    private final ArticleType type;

    public LikeEvent(Object source, Integer communityId, LikeType likeType, ArticleType type) {
        super(source);
        this.communityId =communityId;
        this.likeType = likeType;
        this.type = type;
    }

    public enum LikeType {
        LIKE,
        DISLIKE
    }
}
