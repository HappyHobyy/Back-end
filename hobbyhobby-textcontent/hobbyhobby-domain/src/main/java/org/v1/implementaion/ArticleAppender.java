package org.v1.implementaion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.Article;
import org.v1.model.Content;
import org.v1.repository.ArticleRepository;

@Component
@AllArgsConstructor
public class ArticleAppender {
    private final ArticleRepository articleRepository;

    public Long appendArticle(Article article) {
        return articleRepository.appendArticle(article);
    }
    public void appendArticleContent(Content content, Long articleId) {
        articleRepository.appendArticleContent(content, articleId);
    }
}
