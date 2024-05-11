package org.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementaion.image.ImageAppender;
import org.v1.implementaion.image.ImageProcessor;
import org.v1.implementaion.image.ImageRemover;
import org.v1.implementaion.reviewarticle.ReviewArticleChecker;
import org.v1.implementaion.reviewarticle.ReviewArticleReader;
import org.v1.implementaion.reviewcomment.ReviewCommentAppender;
import org.v1.implementaion.reviewcomment.ReviewCommentReader;
import org.v1.implementaion.reviewcomment.ReviewCommentRemover;
import org.v1.implementaion.reviewcomment.ReviewCommentValidator;
import org.v1.model.*;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class ReviewArticleDetailService {
    private final ReviewArticleReader reviewArticleReader;
    private final ReviewArticleChecker reviewArticleChecker;
    private final ReviewCommentAppender reviewCommentAppender;
    private final ReviewCommentReader reviewCommentReader;
    private final ReviewCommentRemover reviewCommentRemover;
    private final ReviewCommentValidator reviewCommentValidator;
    private final ImageProcessor imageProcessor;

    public void deleteArticleComment(Long commentId) {
        ReviewComment comment = reviewCommentReader.readComment(commentId);
        imageProcessor.removeImage(comment.getImage());
        reviewCommentRemover.removeComment(commentId);
    }
    public Long commentOnArticle(ReviewComment comment, Long articleId) {
        Long commentId = reviewCommentAppender.appendComment(comment, articleId);
        Content.Image image = imageProcessor.appendImage("REVIEW-COMMENT",commentId, comment.getImage());
        reviewCommentAppender.appendImage(image, commentId);
        return commentId;
    }
    public ReviewArticleDetail getArticleDetail(Long articleId, Long userId) {
        UserStatus userStatus = reviewArticleChecker.checkArticleUserRelation(articleId, userId);
        List<ReviewComment> comments = reviewCommentReader.readComments(articleId);
        List<ReviewComment> updatedComments = reviewCommentValidator.validateComments(comments, userId);
        ReviewContent content = reviewArticleReader.readContent(articleId);
        return new ReviewArticleDetail(updatedComments, content, userStatus);
    }

}
