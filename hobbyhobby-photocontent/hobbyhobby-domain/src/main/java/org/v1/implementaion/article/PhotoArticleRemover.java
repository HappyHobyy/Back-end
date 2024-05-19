package org.v1.implementaion.article;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.repository.article.PhotoArticleRepository;

@Component
@AllArgsConstructor
public class PhotoArticleRemover {
    private PhotoArticleRepository repository;
    public void removeArticle(final Long photoArticleId) {
        repository.removeArticle(photoArticleId);
    }
}
