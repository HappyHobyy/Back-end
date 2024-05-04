package org.v1.jparepository;

import org.springframework.stereotype.Repository;
import org.v1.model.Article;
import org.v1.repository.ArticleRepository;
import org.v1.model.Community;
import org.v1.model.Search;

import java.util.List;

@Repository
public class ArticleEntityJpaRepository implements ArticleRepository {

    @Override
    public List<Article> getArticleByCommunity(Long CommunityId) {
        return null;
    }

    @Override
    public List<Article> getArticleBySearch(Search search) {
        return null;
    }

    @Override
    public void saveArticle(Community community) {

    }

    @Override
    public void removeArticle(Long articleId) {

    }
}
