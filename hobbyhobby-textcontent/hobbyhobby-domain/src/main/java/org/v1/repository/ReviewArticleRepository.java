package org.v1.repository;

import org.v1.model.*;

import java.util.List;

public interface ReviewArticleRepository {
    List<ReviewArticle> readArticleByCommunity(Long CommunityId);
    Content readTextContent(Long articleId);
    boolean checkArticleExist(Long articleId);
    UserStatus checkUserRelation(Long articleId, Long userId);
    Long appendArticle(ReviewArticle article);
    void appendArticleContent(Content content, Long articleId);
    void removeArticle(Long articleId);
}
