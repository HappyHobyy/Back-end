package org.v1.implementaion.textarticle;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.Content;
import org.v1.model.TextArticle;
import org.v1.repository.TextArticleRepository;

@Component
@AllArgsConstructor
public class TextArticleAppender {
    private final TextArticleRepository textArticleRepository;
    public Long appendTextArticle(TextArticle article) {
        return textArticleRepository.appendArticle(article);
    }
    public void appendTextArticleContent(Content content, Long articleId) {
        textArticleRepository.appendArticleContent(content, articleId);
    }
}
