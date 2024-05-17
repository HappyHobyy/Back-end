package org.v1.jparepository.gathering;

import org.springframework.stereotype.Repository;
import org.v1.model.comment.Comment;
import org.v1.repository.gathering.GatheringCommentRepository;
import org.v1.repository.photo.PhotoCommentRepository;

import java.util.List;

@Repository
public class GatheringCommentJpaEntityRepository implements GatheringCommentRepository {
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
