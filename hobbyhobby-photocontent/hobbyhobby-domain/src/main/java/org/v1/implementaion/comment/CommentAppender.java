package org.v1.implementaion.comment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.comment.Comment;
import org.v1.repository.comment.PhotoCommentRepository;

@Component
@AllArgsConstructor
public class CommentAppender {
    private final PhotoCommentRepository repository;
    public long appendPhotoArticleComment(final Comment comment, final Long articleId) {
        return repository.appendComment(comment,articleId);
    }
}
