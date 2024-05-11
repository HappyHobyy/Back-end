package org.v1.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementaion.image.ImageProcessor;
import org.v1.implementaion.reviewarticle.ReviewArticleAppender;
import org.v1.implementaion.reviewarticle.ReviewArticleReader;
import org.v1.implementaion.reviewarticle.ReviewArticleRemover;
import org.v1.implementaion.reviewcomment.ReviewCommentReader;
import org.v1.model.Content;
import org.v1.model.ReviewArticle;
import org.v1.model.ReviewComment;
import org.v1.model.ReviewContent;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewArticleService {
    private final ReviewArticleReader reviewArticleReader;
    private final ReviewArticleAppender reviewArticleAppender;
    private final ReviewArticleRemover reviewArticleRemover;
    private final ReviewCommentReader reviewCommentReader;
    private final ImageProcessor imageProcessor;
    public List<ReviewArticle> getRecentTextArticles(Long communityId) {
        return reviewArticleReader.readTenArticle(communityId);
    }
    @Transactional
    public Long createReviewArticle(ReviewArticle article, final ReviewContent content) {
        Long articleId = reviewArticleAppender.appendArticle(article);
        ReviewContent finalContent = content.getImage()
                .map(image -> {
                    Content.Image processedImage = imageProcessor.appendImage(articleId, image);
                    return ReviewContent.content(content.getText().orElse(null), processedImage);
                })
                .orElse(content);
        reviewArticleAppender.appendArticleContent(finalContent, articleId);
        return articleId;
    }

    public void deleteReviewArticle(Long articleId) {
        reviewCommentReader.readComments(articleId).stream()
                .map(ReviewComment::getImage)
                .filter(Objects::nonNull)
                .flatMap(Optional::stream)
                .forEach(imageProcessor::removeImage);
        ReviewContent content = reviewArticleReader.readContent(articleId);
        content.getImage().ifPresent(imageProcessor::removeImage);
        reviewArticleRemover.removeArticle(articleId);
    }
}
