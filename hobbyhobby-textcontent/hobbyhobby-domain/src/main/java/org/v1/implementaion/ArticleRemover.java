package org.v1.implementaion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.repository.ArticleRepository;

@Component
@AllArgsConstructor
public class ArticleRemover {
    private ArticleRepository articleRepository;
    public void removeArticle(final Long articleId) {
        articleRepository.removeArticle(articleId);
    }
}
