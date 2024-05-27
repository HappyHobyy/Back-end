package org.v1.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.v1.model.article.ArticleType;
import org.v1.model.group.GatheringMember;

public record GatheringMemberRequest(
        @Schema(description = "게시물", example = "123")
        @NotNull(message = "댓글 내용은 필수 입력값입니다.")
        Long articleId
) {
    public GatheringMember toSingleGatheringMember(Long userId) {
        return new GatheringMember(articleId,userId, ArticleType.SINGLE_GATHERING);
    }
    public GatheringMember toUnionGatheringMember(Long userId) {
        return new GatheringMember(articleId,userId, ArticleType.UNION_GATHERING);
    }
}
