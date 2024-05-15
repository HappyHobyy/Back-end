package org.v1.repository;

import org.v1.model.like.Like;

public interface LikeRepository {
    boolean checkArticleLike(Like like);
    Integer appendLike(Like like);
    Integer removeLike(Like like);
    Integer countLike(Long articleId);
}
