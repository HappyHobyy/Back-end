package org.v1.implementaion.photo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.photo.PhotoArticle;
import org.v1.model.photo.PhotoAriticleContent;
import org.v1.repository.photo.PhotoArticleRepository;

@Component
@AllArgsConstructor
public class PhotoArticleAppender {
    private final PhotoArticleRepository photoArticleRepository;

    public Long appendPhotoArticle(PhotoArticle photoArticle) {
        return photoArticleRepository.appendArticle(photoArticle);
    }
    public void appendPhotoArticleContent(PhotoAriticleContent photoAriticleContent, Long photoArticleId) {
        photoArticleRepository.appendArticleContent(photoAriticleContent, photoArticleId);
    }
}
