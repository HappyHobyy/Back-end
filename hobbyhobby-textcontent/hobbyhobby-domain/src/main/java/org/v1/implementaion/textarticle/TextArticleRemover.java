package org.v1.implementaion.textarticle;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.repository.TextArticleRepository;

@Component
@AllArgsConstructor
public class TextArticleRemover {
    private TextArticleRepository textArticleRepository;
    public void removeArticle(final Long articleId) {
        textArticleRepository.removeArticle(articleId);
    }
}
