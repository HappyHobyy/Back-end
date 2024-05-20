package org.v1.jparepository.gathering;

import org.springframework.stereotype.Repository;
import org.v1.model.article.ArticleType;
import org.v1.model.article.GatheringInfo;
import org.v1.model.article.GatheringArticle;
import org.v1.model.article.GatheringArticleContent;
import org.v1.model.user.UserStatus;
import org.v1.repository.article.GatheringArticleRepository;

import java.util.List;

@Repository
public class GatheringArticleJpaEntityRepository implements GatheringArticleRepository {

    @Override
    public List<GatheringArticle> readArticleLatest(ArticleType type) {
        return null;
    }

    @Override
    public List<GatheringArticle> readArticleSearch(GatheringInfo info) {
        return null;
    }

    @Override
    public List<GatheringArticle> readPopularCommunityArticle(Integer communityId) {
        return null;
    }

    @Override
    public List<GatheringArticle> readNotPopularCommunityArticle(Integer communityId) {
        return null;
    }

    @Override
    public Long appendArticle(GatheringArticle photoArticle) {
        return null;
    }

    @Override
    public void appendArticleContent(GatheringArticleContent content, GatheringInfo info) {

    }

    @Override
    public void removeArticle(GatheringInfo info) {

    }

    @Override
    public UserStatus checkArticleUserRelation(GatheringInfo info, Long userId) {
        return null;
    }

    @Override
    public boolean checkArticleExist(GatheringInfo info) {
        return false;
    }

    @Override
    public GatheringArticleContent readContent(GatheringInfo info) {
        return null;
    }
}
