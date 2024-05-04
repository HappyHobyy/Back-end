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
    public void saveLike(Like like) {

    }

    @Override
    public void removeLike(Like like) {

    }

    @Override
    public void removeComment(Long CommentId) {

    }

    @Override
    public void saveComment(Comment comment) {

    }
}
