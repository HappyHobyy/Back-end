package org.v1.repository;

import org.v1.model.photoartlcle.PhotoArticle;
import org.v1.model.photoartlcle.PhotoAriticleContent;
import org.v1.model.user.UserStatus;

import java.util.List;

public interface PhotoArticleRepository {
    List<PhotoArticle> readArticleLatest(Integer communityId);
    List<PhotoArticle> readArticleLikes(Integer communityId);
    Long appendArticle(PhotoArticle photoArticle);
    void appendArticleContent(PhotoAriticleContent photoAriticleContent, Long articleId);
    void removeArticle(Long photoArticleId);
    UserStatus checkArticleUserRelation(Long articleId, Long userId);
    boolean checkArticleExist(Long articleId);
    PhotoAriticleContent readContent(Long articleId);
}
