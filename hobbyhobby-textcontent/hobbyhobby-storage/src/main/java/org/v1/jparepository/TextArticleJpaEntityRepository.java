package org.v1.jparepository;

import org.springframework.stereotype.Repository;
import org.v1.model.*;
import org.v1.repository.TextArticleRepository;

import java.util.List;

@Repository
public class TextArticleJpaEntityRepository implements TextArticleRepository {

    @Override
    public List<TextArticle> readArticleByCommunity(Long CommunityId) {
        return null;
    }

    @Override
    public List<TextArticle> readArticleBySearch(Search search) {
        return null;
    }

    @Override
    public Content readTextContent(Long articleId) {
        return null;
    }

    @Override
    public boolean checkArticleExist(Long articleId) {
        return false;
    }

    @Override
    public UserStatus checkUserRelation(Long articleId, Long userId) {
        return null;
    }

    @Override
    public Long appendArticle(TextArticle article) {
        return null;
    }

    @Override
    public void appendArticleContent(Content content, Long articleId) {

    }

    @Override
    public void removeArticle(Long articleId) {

    }
}
