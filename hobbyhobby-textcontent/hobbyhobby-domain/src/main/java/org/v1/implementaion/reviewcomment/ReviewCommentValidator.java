package org.v1.implementaion.reviewcomment;

import org.springframework.stereotype.Service;
import org.v1.model.Comment;
import org.v1.model.ReviewComment;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewCommentValidator {
    public List<ReviewComment> validateComments(List<ReviewComment> comments, Long userId) {
        return comments.stream()
                .map(comment -> comment.changeComment(validateComment(comment.getComment(), userId)))
                .collect(Collectors.toList());
    }

    private Comment validateComment(Comment comment, Long userId) {
        return comment.validateUserComment(userId);
    }
}
