package org.v1.implementaion.textarticle;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.error.BusinessException;
import org.v1.error.ErrorCode;
import org.v1.model.TextArticleDetail;
import org.v1.model.UserStatus;
import org.v1.repository.TextArticleRepository;
@Component
@AllArgsConstructor
public class TextArticleChecker {
    private final TextArticleRepository textArticleRepository;
    public UserStatus checkArticleUserRelation(Long userId, Long articleId) {
        return textArticleRepository.checkUserRelation(userId,articleId);
    }
    public void checkArticleExist(Long articleId) {
        if(!textArticleRepository.checkArticleExist(articleId)){
            throw new BusinessException(ErrorCode.TEXT_ARTICLE_NOT_FOUND);
        };
    }
}
