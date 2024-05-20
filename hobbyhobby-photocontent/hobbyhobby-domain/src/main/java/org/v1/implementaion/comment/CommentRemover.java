package org.v1.implementaion.comment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.repository.comment.PhotoCommentRepository;

@Component
@AllArgsConstructor
public class CommentRemover {
    private final PhotoCommentRepository repository;
    public void removePhotoArticleComment(final Long commentId) {
        repository.removeComment(commentId);
    }
}
