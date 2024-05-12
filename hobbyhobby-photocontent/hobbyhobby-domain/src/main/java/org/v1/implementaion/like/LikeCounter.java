package org.v1.implementaion.like;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.repository.LikeRepository;

@Component
@AllArgsConstructor
public class LikeCounter {
    private final LikeRepository likeRepository;
    public Integer countLike(Long articleId) {
        return likeRepository.countLike(articleId);
    }
}
