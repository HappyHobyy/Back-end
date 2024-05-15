package org.v1.implementaion.photoarticle;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.error.BusinessException;
import org.v1.error.ErrorCode;
import org.v1.model.user.UserStatus;
import org.v1.repository.PhotoArticleRepository;
@Component
@AllArgsConstructor
public class PhotoArticleChecker {
    private final PhotoArticleRepository photoArticleRepository;
    public UserStatus checkArticleUserRelation(Long userId, Long photoArticleId) {
        return photoArticleRepository.checkArticleUserRelation(userId,photoArticleId);
    }
    public void checkArticleExist(Long photoArticleId) {
        if(!photoArticleRepository.checkArticleExist(photoArticleId)){
            throw new BusinessException(ErrorCode.TEXT_ARTICLE_NOT_FOUND);
        };
    }
}
