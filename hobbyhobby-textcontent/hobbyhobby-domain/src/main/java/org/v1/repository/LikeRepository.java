package org.v1.repository;

import org.v1.model.Like;

public interface LikeRepository {
    boolean checkArticleLike(Like like);
    void appendLike(Like like);
    void removeLike(Like like);
    Integer countLike(Long articleId);
}
