package org.v1.implementaion.textcomment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.TextComment;
import org.v1.repository.TextCommentRepository;

@Component
@AllArgsConstructor
public class TextCommentAppender {
    private final TextCommentRepository textCommentRepository;
    public Long appendComment(final TextComment textComment, final Long articleId) {
        return textCommentRepository.appendComment(textComment,articleId);
    }
}
