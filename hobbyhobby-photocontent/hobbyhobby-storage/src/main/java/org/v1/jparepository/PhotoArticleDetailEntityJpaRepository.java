package org.v1.jparepository;

import org.springframework.stereotype.Repository;
import org.v1.model.PhotoArticleDetail;
import org.v1.model.Comment;
import org.v1.model.Content;
import org.v1.model.Like;
import org.v1.repository.PhotoArticleDetailRepository;

import java.util.List;

@Repository
public class PhotoArticleDetailEntityJpaRepository implements PhotoArticleDetailRepository {
    @Override
    public PhotoArticleDetail read(Long photoArticleId) {
        return null;
    }

    @Override
    public PhotoArticleDetail.UserStatus checkArticleUserRelation(Long photoArticleId, Long userId) {
        return null;
    }

    @Override
    public boolean checkArticleExist(Long photoArticleId) {
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
    public Long saveComment(Comment comment, Long photoArticleId) {
        return 1L;
    }

    @Override
    public Content readContent(Long photoArticleId) {
        return null;
    }

    @Override
    public List<Comment> readComments(Long photoArticleId) {
        return null;
    }
}
