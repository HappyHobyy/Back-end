package org.v1.implementaion.comment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.repository.CommentRepository;

@Component
@AllArgsConstructor
public class CommentRemover {
    private final CommentRepository commentRepository;
    public void removeComment(final Long commentId) {
        commentRepository.removeComment(commentId);
    }
}
