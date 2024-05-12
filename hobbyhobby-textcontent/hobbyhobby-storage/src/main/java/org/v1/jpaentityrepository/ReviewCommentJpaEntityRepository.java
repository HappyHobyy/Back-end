package org.v1.jpaentityrepository;

import org.springframework.stereotype.Repository;
import org.v1.model.Comment;
import org.v1.model.Content;
import org.v1.model.ReviewComment;
import org.v1.model.TextComment;
import org.v1.repository.ReviewCommentRepository;
import org.v1.repository.TextCommentRepository;

import java.util.List;

@Repository
public class ReviewCommentJpaEntityRepository implements ReviewCommentRepository {
    @Override
    public Long appendComment(ReviewComment comment, Long articleId) {
        return null;
    }

    @Override
    public void removeComment(Long commentId) {

    }

    @Override
    public void appendImage(Content.Image image, Long commentId) {

    }

    @Override
    public List<ReviewComment> readComments(Long articleId) {
        return null;
    }

    @Override
    public ReviewComment readComment(Long commentId) {
        return null;
    }
}
