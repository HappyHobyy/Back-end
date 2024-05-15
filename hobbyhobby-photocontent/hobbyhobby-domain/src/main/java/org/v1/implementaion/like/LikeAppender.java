package org.v1.implementaion.like;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.like.Like;
import org.v1.repository.LikeRepository;
@Component
@AllArgsConstructor
public class LikeAppender {
    private final LikeRepository likeRepository;
    public Integer appendLike(final Like like) {
        return likeRepository.appendLike(like);
    }
}
