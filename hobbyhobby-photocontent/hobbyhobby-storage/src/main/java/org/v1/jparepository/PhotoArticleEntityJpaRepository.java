package org.v1.jparepository;

import org.springframework.stereotype.Repository;
import org.v1.model.PhotoArticle;
import org.v1.model.Content;
import org.v1.model.UserStatus;
import org.v1.repository.PhotoArticleRepository;

import java.util.List;

@Repository
public class PhotoArticleEntityJpaRepository implements PhotoArticleRepository {

    @Override
    public List<PhotoArticle> readArticleLatest(Integer communityId) {
        return null;
    }

    @Override
    public List<PhotoArticle> readArticleLikes(Integer communityId) {
        return null;
    }

    @Override
    public Long appendArticle(PhotoArticle photoArticle) {
        return null;
    }

    @Override
    public void appendArticleContent(Content content, Long articleId) {

    }

    @Override
    public void removeArticle(Long photoArticleId) {

    }

    @Override
    public UserStatus checkArticleUserRelation(Long articleId, Long userId) {
        return null;
    }

    @Override
    public boolean checkArticleExist(Long articleId) {
        return false;
    }

    @Override
    public Content readContent(Long articleId) {
        return null;
    }
}
