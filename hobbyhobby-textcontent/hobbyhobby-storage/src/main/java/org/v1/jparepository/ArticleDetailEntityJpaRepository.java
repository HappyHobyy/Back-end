package org.v1.jparepository;

import org.springframework.stereotype.Repository;
import org.v1.model.ArticleDetail;
import org.v1.model.Comment;
import org.v1.model.Like;
import org.v1.repository.ArticleDetailRepository;

@Repository
public class ArticleDetailEntityJpaRepository implements ArticleDetailRepository {
    @Override
    public ArticleDetail read(Long articleId) {
        return null;
    }

    @Override
    public ArticleDetail.UserStatus checkArticleUserRelation(Long articleId, Long userId) {
        return null;
    }

    @Override
    public boolean checkArticleExist(Long articleId) {
        return false;
    }

    @Override
    public boolean checkArticleLike(Like like) {
        return false;
    }


    @Override
    public void saveLike(Like like) {

    }

    @Override
    public void removeLike(Like like) {

    }

    @Override
    public void removeComment(Long CommentId) {

    }

    @Override
    public void saveComment(Comment comment, Long articleId) {

    }
}
