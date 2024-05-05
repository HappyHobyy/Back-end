package org.v1.repository;

import org.v1.model.*;

import java.util.List;

public interface ArticleRepository {
    List<Article> getArticleByCommunity(Long CommunityId);
    List<Article> getArticleBySearch(Search search);
    Long appendArticle(Article article);
    void appendArticleContent(Content content, Long articleId);
    void removeArticle(Long articleId);
}
