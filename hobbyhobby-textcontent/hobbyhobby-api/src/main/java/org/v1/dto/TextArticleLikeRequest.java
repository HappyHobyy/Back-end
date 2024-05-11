package org.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.v1.model.Like;

public record TextArticleLikeRequest(
        @Schema(description = "게시글Id", example = "123")
        @NotNull(message = "게시글Id는 필수 입력값입니다.")
        Long articleId
) {
    public Like toLike(Long userId){
        return new Like(userId,this.articleId);
    }
}
