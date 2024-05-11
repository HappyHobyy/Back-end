package org.v1.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementaion.image.ImageRemover;
import org.v1.implementaion.textarticle.TextArticleAppender;
import org.v1.implementaion.textarticle.TextArticleReader;
import org.v1.implementaion.textarticle.TextArticleRemover;
import org.v1.implementaion.image.ImageAppender;
import org.v1.model.Content;
import org.v1.model.Search;
import org.v1.model.TextArticle;
import org.v1.repository.ImageRepository;

import java.io.File;
import java.util.List;

@Service
@AllArgsConstructor
public class TextArticleService {
    private final TextArticleReader textArticleReader;
    private final TextArticleAppender textArticleAppender;
    private final ImageAppender imageAppender;
    private final ImageRemover imageRemover;
    private final TextArticleRemover textArticleRemover;
    public List<TextArticle> getRecentTextArticles(Long communityId) {
        return textArticleReader.readTenArticle(communityId);
    }
    public List<TextArticle> getRecentTextArticlesBySearch(Search search) {
        return textArticleReader.readTenSearchArticle(search);
    }
    @Transactional
    public Long createTextArticle(TextArticle article, Content content) {
        Long articleId = textArticleAppender.appendTextArticle(article);
        List<Content.Image> imageList = content.getImages().stream()
                .map(image -> {
                    String path = imageAppender.appendImage(image.data(), articleId.toString());
                    return Content.Image.withoutData(image.index(), path);
                })
                .toList();
        textArticleAppender.appendTextArticleContent(new Content(content.getTexts(), imageList).calculateIndex(), articleId);
        return articleId;
    }
    public void deleteTextArticle(Long articleId){
        textArticleReader.readContent(articleId).getImages().forEach(image -> {
            imageRemover.removeImage(image.path());
        });
        textArticleRemover.removeArticle(articleId);
    }
}
