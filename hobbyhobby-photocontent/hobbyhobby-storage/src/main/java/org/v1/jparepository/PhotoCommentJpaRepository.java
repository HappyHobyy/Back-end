package org.v1.jparepository;

import org.springframework.stereotype.Repository;
import org.v1.model.Comment;
import org.v1.repository.CommentRepository;

import java.util.List;
@Repository
public class PhotoCommentJpaRepository implements CommentRepository {
    @Override
    public List<Comment> readComments(Long articleId) {
        return null;
    }

    @Override
    public Long appendComment(Comment comment, Long articleId) {
        return null;
    }

    @Override
    public void removeComment(Long commentId) {

    }

    @Override
    public Integer countComment(Long articleId) {
        return null;
    }
}
