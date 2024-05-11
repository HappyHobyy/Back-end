package org.v1.implementaion.comment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.repository.TextCommentRepository;
@Component
@AllArgsConstructor
public class TextCommentRemover {
    private final TextCommentRepository textCommentRepository;
    public void removeComment(final Long CommentId) {
        textCommentRepository.removeComment(CommentId);
    }
}
