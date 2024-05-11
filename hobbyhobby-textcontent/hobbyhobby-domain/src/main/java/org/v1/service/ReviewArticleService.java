package org.v1.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementaion.image.ImageAppender;
import org.v1.implementaion.image.ImageProcessor;
import org.v1.implementaion.image.ImageRemover;
import org.v1.implementaion.reviewarticle.ReviewArticleAppender;
import org.v1.implementaion.reviewarticle.ReviewArticleReader;
import org.v1.implementaion.reviewarticle.ReviewArticleRemover;
import org.v1.implementaion.textarticle.TextArticleAppender;
import org.v1.implementaion.textarticle.TextArticleReader;
import org.v1.implementaion.textarticle.TextArticleRemover;
import org.v1.model.Content;
import org.v1.model.ReviewArticle;
import org.v1.model.Search;
import org.v1.model.TextArticle;
import org.v1.repository.ImageRepository;

import java.util.List;
@Service
@AllArgsConstructor
public class ReviewArticleService {
    private final ReviewArticleReader reviewArticleReader;
    private final ReviewArticleAppender reviewArticleAppender;
    private final ReviewArticleRemover reviewArticleRemover;
    private final ImageProcessor imageProcessor;
    public List<ReviewArticle> getRecentTextArticles(Long communityId) {
        return reviewArticleReader.readTenArticle(communityId);
    }
    @Transactional
    public Long createReviewArticle(ReviewArticle article, Content content) {
        Long articleId = reviewArticleAppender.appendArticle(article);
        List<Content.Image> imageList = imageProcessor.appendImages("REVIEW",articleId, content.getImages());
        reviewArticleAppender.appendArticleContent(new Content(content.getTexts(), imageList).calculateIndex(), articleId);
        return articleId;
    }
    public void deleteReviewArticle(Long articleId){
        imageProcessor.removeImages(reviewArticleReader.readContent(articleId).getImages());
        reviewArticleRemover.removeArticle(articleId);
    }
}
