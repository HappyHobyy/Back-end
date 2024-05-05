package org.v1.jparepository;

import org.springframework.stereotype.Repository;
import org.v1.model.*;
import org.v1.repository.ArticleRepository;

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
    public Long appendArticle(Article article) {
        return 1L;
    }

    @Override
    public void appendArticleContent(Content content, Long articleId) {

    }

    @Override
    public void removeArticle(Long articleId) {

    }
}
