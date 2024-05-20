package org.v1.jparepository.photo;

import org.springframework.stereotype.Repository;
import org.v1.model.comment.Comment;
import org.v1.repository.comment.PhotoCommentRepository;

import java.util.List;
@Repository
public class PhotoCommentJpaEntityRepository implements PhotoCommentRepository {
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
