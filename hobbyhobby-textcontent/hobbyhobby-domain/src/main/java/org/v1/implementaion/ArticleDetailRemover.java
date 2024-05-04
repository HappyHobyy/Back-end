package org.v1.implementaion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.ArticleDetail;
import org.v1.model.Comment;
import org.v1.model.Like;
import org.v1.repository.ArticleDetailRepository;

@Component
@AllArgsConstructor
public class ArticleDetailRemover {
    private final ArticleDetailRepository articleDetailRepository;
    public void removeLike(final Like like) {
        articleDetailRepository.removeLike(like);
    }

    public void removeComment(final Long CommentId) {
        articleDetailRepository.removeComment(CommentId);
    }
}
