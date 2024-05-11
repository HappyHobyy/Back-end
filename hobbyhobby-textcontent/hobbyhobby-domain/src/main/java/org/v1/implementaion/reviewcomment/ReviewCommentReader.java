package org.v1.implementaion.reviewcomment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.ReviewComment;
import org.v1.model.TextComment;
import org.v1.repository.ReviewCommentRepository;
import org.v1.repository.TextCommentRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class ReviewCommentReader {
    private final ReviewCommentRepository reviewCommentRepository;
    public List<ReviewComment> readComments(Long articleId) {
        return reviewCommentRepository.readComments(articleId);
    }
    public ReviewComment readComment(Long commentId) {
        return reviewCommentRepository.readComment(commentId);
    }
}
