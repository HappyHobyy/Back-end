package org.v1.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.v1.model.article.ArticleType;
import org.v1.model.like.Like;

public record ArticleLikeRequest(
        @Schema(description = "게시글Id", example = "123")
        @NotNull(message = "게시글Id는 필수 입력값입니다.")
        Long articleId
) {
    public Like toSingle(Long userId) {
        return Like.toSingleGatheringLike(userId, articleId);
    }

    public Like toMulti(Long userId) {
        return Like.toUnionGatheringLike(userId, articleId);
    }

    public Like toPhoto(Long userId) {
        return Like.toSingleGatheringLike(userId, this.articleId);
    }
}
