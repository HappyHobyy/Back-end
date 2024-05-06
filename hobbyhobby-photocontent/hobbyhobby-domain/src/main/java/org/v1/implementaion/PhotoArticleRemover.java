package org.v1.implementaion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.repository.PhotoArticleRepository;

@Component
@AllArgsConstructor
public class PhotoArticleRemover {
    private PhotoArticleRepository photoArticleRepository;
    public void removeArticle(final Long photoArticleId) {
        photoArticleRepository.removeArticle(photoArticleId);
    }
}
