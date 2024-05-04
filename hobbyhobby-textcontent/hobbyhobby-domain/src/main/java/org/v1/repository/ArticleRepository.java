package org.v1.repository;

import org.v1.model.Article;
import org.v1.model.ArticleDetail;
import org.v1.model.Community;
import org.v1.model.Search;

import java.util.List;

public interface ArticleRepository {
    List<Article> getArticleByCommunity(Long CommunityId);
    List<Article> getArticleBySearch(Search search);
    void saveArticle(Community community);
    void removeArticle(Long articleId);
}
