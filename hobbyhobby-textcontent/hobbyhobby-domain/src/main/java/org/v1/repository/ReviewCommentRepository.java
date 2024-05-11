package org.v1.repository;

import org.v1.model.Comment;
import org.v1.model.Content;
import org.v1.model.ReviewComment;

import java.util.List;

public interface ReviewCommentRepository {
    Long appendComment(Comment comment, Long articleId);
    void removeComment(Long commentId);
    void appendContent(Content content, Long commentId);
    List<ReviewComment> readComments(Long articleId);
    ReviewComment readComment(Long commentId);
}
