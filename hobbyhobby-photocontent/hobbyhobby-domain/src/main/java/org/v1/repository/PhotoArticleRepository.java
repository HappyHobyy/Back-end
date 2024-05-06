package org.v1.repository;

import org.v1.model.PhotoArticle;
import org.v1.model.Content;

import java.util.List;

public interface PhotoArticleRepository {
    List<PhotoArticle> readArticleLatest(Long CommunityId);
    List<PhotoArticle> readArticleLikes(Long CommunityId);
    Long appendArticle(PhotoArticle photoArticle);
    void appendArticleContent(Content content, Long photophotoArticleId);
    void removeArticle(Long photoArticleId);
}
