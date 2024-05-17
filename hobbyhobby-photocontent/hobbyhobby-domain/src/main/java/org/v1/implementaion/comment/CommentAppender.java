package org.v1.implementaion.comment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.comment.Comment;
import org.v1.repository.photo.PhotoCommentRepository;

@Component
@AllArgsConstructor
public class CommentAppender {
    private final PhotoCommentRepository photoCommentRepository;
    public Long appendPhotoArticleComment(final Comment comment, final Long articleId) {
        return photoCommentRepository.appendComment(comment,articleId);
    }
    public Long appendGatheringArticleComment(final Comment comment, final Long articleId) {
        return photoCommentRepository.appendComment(comment,articleId);
    }
}
