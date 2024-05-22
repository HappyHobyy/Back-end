package org.v1.repository.comment;

import org.v1.model.comment.Comment;

import java.util.List;

public interface PhotoCommentRepository {
    List<Comment> readComments(Long articleId);
    long appendComment(Comment comment,Long articleId);
    void removeComment(Long commentId);
}
