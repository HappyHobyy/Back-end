package org.v1.implementaion.reviewarticle;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.error.BusinessException;
import org.v1.error.ErrorCode;
import org.v1.model.UserStatus;
import org.v1.repository.ReviewArticleRepository;
import org.v1.repository.TextArticleRepository;

@Component
@AllArgsConstructor
public class ReviewArticleChecker {
    private final ReviewArticleRepository reviewArticleRepository;

    public UserStatus checkArticleUserRelation(Long userId, Long articleId) {
        return reviewArticleRepository.checkUserRelation(userId,articleId);
    }
    public void checkArticleExist(Long articleId) {
        if(!reviewArticleRepository.checkArticleExist(articleId)){
            throw new BusinessException(ErrorCode.REVIEW_ARTICLE_NOT_FOUND);
        };
    }
}
