package org.v1.implementaion.comment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.comment.Comment;
import org.v1.repository.comment.PhotoCommentRepository;
import java.util.List;

@Component
@AllArgsConstructor
public class CommentReader {
    private final PhotoCommentRepository repository;
    public List<Comment> readPhotoArticleComments(Long articleId) {
        return repository.readComments(articleId);
    }
}
