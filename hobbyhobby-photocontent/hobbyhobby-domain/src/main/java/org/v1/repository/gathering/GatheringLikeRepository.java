package org.v1.repository.gathering;

import org.v1.model.like.Like;

public interface GatheringLikeRepository {
    boolean checkArticleLike(Like like);
    Integer appendLike(Like like);
    Integer removeLike(Like like);
    Integer countLike(Long articleId);
}
