package org.v1.implementaion.like;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.error.BusinessException;
import org.v1.error.ErrorCode;
import org.v1.model.like.Like;
import org.v1.repository.like.LikeRepository;

@Component
@AllArgsConstructor
public class LikeChecker {
    private final LikeRepository repository;
    public void checkArticleLikeToAppend(Like like) {
        if(repository.checkArticleLike(like)){
            throw new BusinessException(ErrorCode.ARTICLE_ALREADY_LIKE);
        };
    }
    public void checkArticleLikeToRemove(Like like) {
        if(!repository.checkArticleLike(like)){
            throw new BusinessException(ErrorCode.ARTICLE_ALREADY_DISLIKE);
        };
    }
}
