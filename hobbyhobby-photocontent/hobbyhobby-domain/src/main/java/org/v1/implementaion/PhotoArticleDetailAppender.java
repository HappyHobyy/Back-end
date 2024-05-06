package org.v1.implementaion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.Comment;
import org.v1.model.Like;
import org.v1.repository.PhotoArticleDetailRepository;


@Component
@AllArgsConstructor
public class PhotoArticleDetailAppender {
    private final PhotoArticleDetailRepository photoArticleDetailRepository;
    public void appendLike(final Like like) {
        photoArticleDetailRepository.saveLike(like);
    }
    public Long appendComment(final Comment comment,final Long photoArticleId) {
        return photoArticleDetailRepository.saveComment(comment,photoArticleId);
    }
}
