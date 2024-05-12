package org.v1.implementaion.photoarticle;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.PhotoArticle;
import org.v1.model.Content;
import org.v1.repository.PhotoArticleRepository;

@Component
@AllArgsConstructor
public class PhotoArticleAppender {
    private final PhotoArticleRepository photoArticleRepository;

    public Long appendPhotoArticle(PhotoArticle photoArticle) {
        return photoArticleRepository.appendArticle(photoArticle);
    }
    public void appendPhotoArticleContent(Content content, Long photoArticleId) {
        photoArticleRepository.appendArticleContent(content, photoArticleId);
    }
}
