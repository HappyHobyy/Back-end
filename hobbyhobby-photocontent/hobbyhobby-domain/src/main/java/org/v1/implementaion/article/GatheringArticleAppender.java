package org.v1.implementaion.article;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.article.*;
import org.v1.repository.article.GatheringArticleRepository;

@Component
@AllArgsConstructor
public class GatheringArticleAppender {
    private final GatheringArticleRepository repository;
    public Long appendArticle(GatheringArticle article) {
        return repository.appendArticle(article);
    }
    public void appendArticleContent(GatheringArticleContent content, GatheringInfo info) {
        repository.appendArticleContent(content, info);
    }
}
