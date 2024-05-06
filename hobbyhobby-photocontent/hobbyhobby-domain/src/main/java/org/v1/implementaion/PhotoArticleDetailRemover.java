package org.v1.implementaion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.Like;
import org.v1.repository.PhotoArticleDetailRepository;

@Component
@AllArgsConstructor
public class PhotoArticleDetailRemover {
    private final PhotoArticleDetailRepository photoArticleDetailRepository;
    public void removeLike(final Like like) {
        photoArticleDetailRepository.removeLike(like);
    }

    public void removeComment(final Long CommentId) {
        photoArticleDetailRepository.removeComment(CommentId);
    }
}
