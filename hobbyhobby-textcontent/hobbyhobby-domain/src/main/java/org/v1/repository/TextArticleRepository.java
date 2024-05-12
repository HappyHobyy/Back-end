package org.v1.repository;

import org.v1.model.*;

import java.util.List;

public interface TextArticleRepository {
    List<TextArticle> readArticleByCommunity(Long CommunityId);
    List<TextArticle> readArticleBySearch(Search search);
    Content readTextContent(Long articleId);
    boolean checkArticleExist(Long articleId);
    UserStatus checkUserRelation(Long articleId, Long userId);
    Long appendArticle(TextArticle article);
    void appendArticleContent(Content content, Long articleId);
    void removeArticle(Long articleId);
}
