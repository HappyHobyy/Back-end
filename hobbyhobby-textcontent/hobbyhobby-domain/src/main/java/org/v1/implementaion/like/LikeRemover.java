package org.v1.implementaion.like;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.Like;
import org.v1.repository.LikeRepository;

@Component
@AllArgsConstructor
public class LikeRemover {
    private final LikeRepository likeRepository;
    public void removeLike(final Like like) {
        likeRepository.removeLike(like);
    }
}
