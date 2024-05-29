package org.v1.repository.like;

import org.v1.model.like.Like;

import java.util.List;

public interface LikeRepository {
    boolean checkArticleLike(Like like);
    List<Integer> appendLike(Like like);
    List<Integer> removeLike(Like like);
}
