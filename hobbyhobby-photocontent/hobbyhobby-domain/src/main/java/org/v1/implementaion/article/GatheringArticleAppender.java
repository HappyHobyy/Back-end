package org.v1.implementaion.article;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.v1.model.article.*;
import org.v1.model.imageVideo.ImageVideo;
import org.v1.repository.article.GatheringArticleRepository;

@Component
@AllArgsConstructor
public class GatheringArticleAppender {
    private final GatheringArticleRepository repository;
    @Transactional
    public Long appendArticle(GatheringArticle article,GatheringArticleContent content) {
        return repository.appendArticle(article, content);
    }
    @Transactional
    public void appendArticleImage(ImageVideo imageVideo, GatheringInfo info) {
        repository.appendArticleImage(imageVideo, info);
    }
}
