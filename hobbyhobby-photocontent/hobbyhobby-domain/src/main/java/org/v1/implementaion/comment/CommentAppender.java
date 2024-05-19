package org.v1.implementaion.comment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.comment.Comment;
import org.v1.repository.CommentRepository;

@Component
@AllArgsConstructor
public class CommentAppender {
    private final CommentRepository commentRepository;
    public Long appendComment(final Comment comment, final Long articleId) {
        return commentRepository.appendComment(comment,articleId);
    }
}
