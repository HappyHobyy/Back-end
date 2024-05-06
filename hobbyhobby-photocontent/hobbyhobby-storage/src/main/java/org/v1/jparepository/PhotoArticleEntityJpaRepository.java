package org.v1.jparepository;

import org.springframework.stereotype.Repository;
import org.v1.model.PhotoArticle;
import org.v1.model.Content;
import org.v1.repository.PhotoArticleRepository;

import java.util.List;

@Repository
public class PhotoArticleEntityJpaRepository implements PhotoArticleRepository {

    @Override
    public List<PhotoArticle> readArticleLatest(Long CommunityId) {
        return null;
    }

    @Override
    public List<PhotoArticle> readArticleLikes(Long CommunityId) {
        return null;
    }

    @Override
    public Long appendArticle(PhotoArticle photoArticle) {
        return 1L;
    }

    @Override
    public void appendArticleContent(Content content, Long photoArticleId) {

    }

    @Override
    public void removeArticle(Long photoArticleId) {

    }
}
