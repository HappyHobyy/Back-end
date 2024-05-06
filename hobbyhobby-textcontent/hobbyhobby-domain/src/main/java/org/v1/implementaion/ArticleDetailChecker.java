package org.v1.implementaion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.error.BusinessException;
import org.v1.error.ErrorCode;
import org.v1.model.ArticleDetail;
import org.v1.model.Like;
import org.v1.repository.ArticleDetailRepository;
import org.v1.repository.ArticleRepository;

@Component
@AllArgsConstructor
public class ArticleDetailChecker {
    private ArticleDetailRepository articleDetailRepository;
    public ArticleDetail.UserStatus checkArticleUserRelation(Long userId,Long articleId) {
        return articleDetailRepository.checkArticleUserRelation(userId,articleId);
    }
    public void checkArticleExist(Long articleId) {
        if(!articleDetailRepository.checkArticleExist(articleId)){
            throw new BusinessException(ErrorCode.TEXT_ARTICLE_NOT_FOUND);
        };
    }
    public void checkArticleLikeToAppend(Like like) {
        if(articleDetailRepository.checkArticleLike(like)){
            throw new BusinessException(ErrorCode.TEXT_ALREADY_DISLIKE);
        };
    }
    public void checkArticleLikeToRemove(Like like) {
        if(!articleDetailRepository.checkArticleLike(like)){
            throw new BusinessException(ErrorCode.TEXT_ALREADY_DISLIKE);
        };
    }
}
