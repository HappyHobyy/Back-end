package org.v1.implementaion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.error.BusinessException;
import org.v1.error.ErrorCode;
import org.v1.model.PhotoArticleDetail;
import org.v1.model.Like;
import org.v1.repository.PhotoArticleDetailRepository;

@Component
@AllArgsConstructor
public class PhotoArticleDetailChecker {
    private PhotoArticleDetailRepository photoArticleDetailRepository;
    public PhotoArticleDetail.UserStatus checkArticleUserRelation(Long userId, Long photoArticleId) {
        return photoArticleDetailRepository.checkArticleUserRelation(userId,photoArticleId);
    }
    public void checkArticleExist(Long photoArticleId) {
        if(!photoArticleDetailRepository.checkArticleExist(photoArticleId)){
            throw new BusinessException(ErrorCode.TEXT_ARTICLE_NOT_FOUND);
        };
    }
    public void checkArticleLikeToAppend(Like like) {
        if(photoArticleDetailRepository.checkArticleLike(like)){
            throw new BusinessException(ErrorCode.TEXT_ALREADY_DISLIKE);
        };
    }
    public void checkArticleLikeToRemove(Like like) {
        if(!photoArticleDetailRepository.checkArticleLike(like)){
            throw new BusinessException(ErrorCode.TEXT_ALREADY_DISLIKE);
        };
    }
}
