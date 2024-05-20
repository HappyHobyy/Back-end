package org.v1.model.like;

import org.v1.model.article.ArticleType;

public record Like(Long userId, Long articleId, ArticleType type) {
    public static Like toSingleGatheringLike(Long userId, Long articleId) {
        return new Like(userId, articleId, ArticleType.SINGLE_GATHERING);
    }
    public static Like toMultiGatheringLike(Long userId, Long articleId) {
        return new Like(userId, articleId, ArticleType.MULTI_GATHERING);
    }
    public static Like toPhotoLike(Long userId, Long articleId) {
        return new Like(userId, articleId, ArticleType.H_LOG);
    }
}
