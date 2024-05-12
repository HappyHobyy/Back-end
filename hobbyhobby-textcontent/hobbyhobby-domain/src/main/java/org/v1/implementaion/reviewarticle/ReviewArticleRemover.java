package org.v1.implementaion.reviewarticle;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.repository.ReviewArticleRepository;
import org.v1.repository.TextArticleRepository;

@Component
@AllArgsConstructor
public class ReviewArticleRemover {
    private final ReviewArticleRepository reviewArticleRepository;
    public void removeArticle(final Long articleId) {
        reviewArticleRepository.removeArticle(articleId);
    }
}
