package org.v1.implementaion.like;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.error.BusinessException;
import org.v1.error.ErrorCode;
import org.v1.model.Like;
import org.v1.repository.LikeRepository;

@Component
@AllArgsConstructor
public class LikeChecker {
    private final LikeRepository likeRepository;
    public void checkArticleLikeToAppend(Like like) {
        if(likeRepository.checkArticleLike(like)){
            throw new BusinessException(ErrorCode.ARTICLE_ALREADY_LIKE);
        };
    }
    public void checkArticleLikeToRemove(Like like) {
        if(!likeRepository.checkArticleLike(like)){
            throw new BusinessException(ErrorCode.ARTICLE_ALREADY_DISLIKE);
        };
    }
}
