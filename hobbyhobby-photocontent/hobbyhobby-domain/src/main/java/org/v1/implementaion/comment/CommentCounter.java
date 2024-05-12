package org.v1.implementaion.comment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.repository.CommentRepository;
import org.v1.repository.LikeRepository;
@Component
@AllArgsConstructor
public class CommentCounter {
    private final CommentRepository commentRepository;
    public Integer countComment(Long articleId) {
        return commentRepository.countComment(articleId);
    }
}
