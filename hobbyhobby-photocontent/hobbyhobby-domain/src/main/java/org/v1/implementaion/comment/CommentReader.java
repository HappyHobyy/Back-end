package org.v1.implementaion.comment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.comment.Comment;
import org.v1.repository.CommentRepository;


import java.util.List;

@Component
@AllArgsConstructor
public class CommentReader {
    private final CommentRepository commentRepository;
    public List<Comment> readComments(Long articleId) {
        return commentRepository.readComments(articleId);
    }

}
