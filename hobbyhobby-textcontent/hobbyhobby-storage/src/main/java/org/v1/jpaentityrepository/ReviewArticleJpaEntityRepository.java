package org.v1.jpaentityrepository;

import org.springframework.stereotype.Repository;
import org.v1.model.*;
import org.v1.repository.ReviewArticleRepository;
import org.v1.repository.TextArticleRepository;

import java.util.List;

@Repository
public class ReviewArticleJpaEntityRepository implements ReviewArticleRepository {


    @Override
    public List<ReviewArticle> readArticleByCommunity(Long CommunityId) {
        return null;
    }

    @Override
    public ReviewContent readReviewContent(Long articleId) {
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
    public Long appendArticle(ReviewArticle article) {
        return null;
    }

    @Override
    public void appendArticleContent(ReviewContent content, Long articleId) {

    }

    @Override
    public void removeArticle(Long articleId) {

    }
}
