package org.v1.implementaion.article;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.error.BusinessException;
import org.v1.error.ErrorCode;
import org.v1.model.user.UserStatus;
import org.v1.repository.article.PhotoArticleRepository;
@Component
@AllArgsConstructor
public class PhotoArticleChecker {
    private final PhotoArticleRepository repository;
    public UserStatus checkArticleUserRelation(Long userId, Long photoArticleId) {
        return repository.checkArticleUserRelation(userId,photoArticleId);
    }
    public void checkArticleExist(Long photoArticleId) {
        if(!repository.checkArticleExist(photoArticleId)){
            throw new BusinessException(ErrorCode.TEXT_ARTICLE_NOT_FOUND);
        };
    }
}
