package org.v1.implementaion.article;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.article.PhotoArticle;
import org.v1.model.imageVideo.ImageVideo;
import org.v1.repository.article.PhotoArticleRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class PhotoArticleAppender {
    private final PhotoArticleRepository repository;
    public long appendArticle(PhotoArticle photoArticle) {
        return repository.appendArticle(photoArticle);
    }
    public void appendArticleContent(List<ImageVideo> imageVideoList , Long photoArticleId) {
        repository.appendArticleContent(imageVideoList, photoArticleId);
    }
}
