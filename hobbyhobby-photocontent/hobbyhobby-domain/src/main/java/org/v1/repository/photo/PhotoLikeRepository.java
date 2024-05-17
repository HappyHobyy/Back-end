package org.v1.repository.photo;

import org.v1.model.like.Like;

public interface PhotoLikeRepository {
    boolean checkArticleLike(Like like);
    Integer appendLike(Like like);
    Integer removeLike(Like like);
    Integer countLike(Long articleId);
}
