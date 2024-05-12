package org.v1.repository;

import org.v1.model.PhotoArticle;
import org.v1.model.Content;
import org.v1.model.UserStatus;

import java.util.List;

public interface PhotoArticleRepository {
    List<PhotoArticle> readArticleLatest(Integer communityId);
    List<PhotoArticle> readArticleLikes(Integer communityId);
    Long appendArticle(PhotoArticle photoArticle);
    void appendArticleContent(Content content, Long articleId);
    void removeArticle(Long photoArticleId);
    UserStatus checkArticleUserRelation(Long articleId, Long userId);
    boolean checkArticleExist(Long articleId);
    Content readContent(Long articleId);
}
