package org.v1.implementaion.like;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.like.Like;
import org.v1.repository.like.LikeRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class LikeRemover {
    private final LikeRepository repository;
    public List<Integer> removeArticleLike(final Like like) {
        return repository.removeLike(like);
    }
}
