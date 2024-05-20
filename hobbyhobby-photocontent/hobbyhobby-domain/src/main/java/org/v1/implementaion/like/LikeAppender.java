package org.v1.implementaion.like;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.like.Like;
import org.v1.repository.like.LikeRepository;

@Component
@AllArgsConstructor
public class LikeAppender {
    private final LikeRepository repository;
    public Integer appendArticleLike(final Like like) {
        return repository.appendLike(like);
    }
}
