package org.v1.implementaion.reviewcomment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.Comment;
import org.v1.model.Content;
import org.v1.model.ReviewComment;
import org.v1.repository.ReviewCommentRepository;

@Component
@AllArgsConstructor
public class ReviewCommentAppender {
    private final ReviewCommentRepository reviewCommentRepository;
    public Long appendComment(final ReviewComment comment, final Long articleId) {
        return reviewCommentRepository.appendComment(comment,articleId);
    }
    public void appendImage(final Content.Image image, final Long commentId) {
        reviewCommentRepository.appendImage(image,commentId);
    }
}
