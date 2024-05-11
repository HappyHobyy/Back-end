package org.v1.implementaion.reviewarticle;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.*;
import org.v1.repository.ReviewArticleRepository;
import org.v1.repository.TextArticleRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class ReviewArticleReader {
    private final ReviewArticleRepository reviewArticleRepository;
    public List<ReviewArticle> readTenArticle(Long communityId) {
        return reviewArticleRepository.readArticleByCommunity(communityId);
    }
    public ReviewContent readContent(Long articleId) {
        return reviewArticleRepository.readReviewContent(articleId);
    }
}
