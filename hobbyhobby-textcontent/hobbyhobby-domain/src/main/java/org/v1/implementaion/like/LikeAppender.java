package org.v1.implementaion.like;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.Like;
import org.v1.repository.LikeRepository;
@Component
@AllArgsConstructor
public class LikeAppender {
    private final LikeRepository likeRepository;
    public void appendLike(final Like like) {
        likeRepository.appendLike(like);
    }
}
