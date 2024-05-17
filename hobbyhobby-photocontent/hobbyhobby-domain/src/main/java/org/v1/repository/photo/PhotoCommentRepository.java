package org.v1.repository.photo;

import org.v1.model.comment.Comment;

import java.util.List;

public interface PhotoCommentRepository {
    List<Comment> readComments(Long articleId);
    Long appendComment(Comment comment,Long articleId);
    void removeComment(Long commentId);
    Integer countComment(Long articleId);
}
