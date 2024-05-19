package org.v1.implementaion.article;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.article.PhotoArticle;
import org.v1.model.article.PhotoAriticleContent;
import org.v1.repository.article.PhotoArticleRepository;

@Component
@AllArgsConstructor
public class PhotoArticleAppender {
    private final PhotoArticleRepository repository;

    public Long appendArticle(PhotoArticle photoArticle) {
        return repository.appendArticle(photoArticle);
    }
    public void appendArticleContent(PhotoAriticleContent photoAriticleContent, Long photoArticleId) {
        repository.appendArticleContent(photoAriticleContent, photoArticleId);
    }
}
