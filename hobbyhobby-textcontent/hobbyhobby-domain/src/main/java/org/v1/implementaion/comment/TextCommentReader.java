package org.v1.implementaion.comment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.Comment;
import org.v1.model.TextComment;
import org.v1.repository.TextCommentRepository;

import java.util.List;
@Component
@AllArgsConstructor
public class TextCommentReader {
    private final TextCommentRepository textCommentRepository;
    public List<TextComment> readComments(Long articleId) {
        return textCommentRepository.readComments(articleId);
    }

}
