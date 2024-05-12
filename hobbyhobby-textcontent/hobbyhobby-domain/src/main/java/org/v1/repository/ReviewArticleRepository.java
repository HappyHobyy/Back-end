package org.v1.repository;

import org.v1.model.*;

import java.util.List;

public interface ReviewArticleRepository {
    List<ReviewArticle> readArticleByCommunity(Long CommunityId);
    ReviewContent readReviewContent(Long articleId);
    boolean checkArticleExist(Long articleId);
    UserStatus checkUserRelation(Long articleId, Long userId);
    Long appendArticle(ReviewArticle article);
    void appendArticleContent(ReviewContent content, Long articleId);
    void removeArticle(Long articleId);
}
