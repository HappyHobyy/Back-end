package org.v1.implementaion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.ArticleDetail;
import org.v1.model.Comment;
import org.v1.model.Like;
import org.v1.repository.ArticleDetailRepository;


@Component
@AllArgsConstructor
public class ArticleDetailAppender {
    private final ArticleDetailRepository articleDetailRepository;
    public void appendLike(final Like like) {
        articleDetailRepository.saveLike(like);
    }
    public void appendComment(final Comment comment) {
        articleDetailRepository.saveComment(comment);
    }
}
