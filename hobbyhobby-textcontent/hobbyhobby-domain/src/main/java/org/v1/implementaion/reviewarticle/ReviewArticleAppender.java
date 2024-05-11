package org.v1.implementaion.reviewarticle;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.Content;
import org.v1.model.ReviewArticle;
import org.v1.model.TextArticle;
import org.v1.repository.ReviewArticleRepository;
import org.v1.repository.TextArticleRepository;

@Component
@AllArgsConstructor
public class ReviewArticleAppender {
    private final ReviewArticleRepository reviewArticleRepository;
    public Long appendArticle(ReviewArticle article) {
        return reviewArticleRepository.appendArticle(article);
    }
    public void appendArticleContent(Content content, Long articleId) {
        reviewArticleRepository.appendArticleContent(content, articleId);
    }
}
