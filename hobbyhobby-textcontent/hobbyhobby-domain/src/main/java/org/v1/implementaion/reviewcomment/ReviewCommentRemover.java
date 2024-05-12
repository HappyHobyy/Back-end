package org.v1.implementaion.reviewcomment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.repository.ReviewCommentRepository;
import org.v1.repository.TextCommentRepository;

@Component
@AllArgsConstructor
public class ReviewCommentRemover {
    private final ReviewCommentRepository reviewCommentRepository;
    public void removeComment(final Long commentId) {
        reviewCommentRepository.removeComment(commentId);
    }
}
