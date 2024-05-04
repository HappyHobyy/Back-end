package org.v1.dto;

import org.v1.model.Comment;

import java.time.LocalDateTime;

public record ArticleCommentRequest(
        Create create,
        Delete delete
) {
    public record Create(Long articleId,String comment,Long userId) {
        public Comment toComment(Long userId) {
            return new Comment(articleId, null, LocalDateTime.now(), comment, false);
        }
    }
    public record Delete(Long commentId) {}
}
