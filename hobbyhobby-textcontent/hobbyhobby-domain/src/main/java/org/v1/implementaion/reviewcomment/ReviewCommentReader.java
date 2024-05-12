package org.v1.implementaion.reviewcomment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.error.BusinessException;
import org.v1.error.ErrorCode;
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
        ReviewComment comment = reviewCommentRepository.readComment(commentId);
        if(comment == null){
            throw new BusinessException(ErrorCode.REVIEW_COMMENT_NOT_FOUND);
        }
        return comment;
    }
}
