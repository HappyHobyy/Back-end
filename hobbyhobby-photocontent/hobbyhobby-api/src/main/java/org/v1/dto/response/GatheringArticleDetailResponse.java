package org.v1.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.v1.model.article.GatheringArticleDetail;

import java.time.LocalDateTime;
import java.util.List;

public record GatheringArticleDetailResponse(
        @Schema(description = "모임 날짜", example = "2024-05-06T15:23:45.123456789")
        @NotNull(message = "필수")
        LocalDateTime date,
        @Schema(description = "내용", example = "text")
        @NotNull(message = "필수")
        String text,
        @Schema(description = "장소", example = "location")
        @NotNull(message = "필수")
        String location,
        @Schema(description = "게시글 작성자 유무", example = "true")
        boolean isUserArticleOwner,
        @Schema(description = "게시글 좋아요 유무", example = "true")
        boolean isUserLiked,
        @Schema(description = "유저 모임 참여 유무", example = "true")
        boolean isUserJoined
) {
    public static GatheringArticleDetailResponse of(GatheringArticleDetail detail) {
        return new GatheringArticleDetailResponse(
                detail.getContent().getLocalDateTime(),
                detail.getContent().getDescription(),
                detail.getContent().getLocation(),
                detail.getUserStatus().isUserOwner(),
                detail.getUserStatus().isUserLiked(),
                detail.getUserStatus().isUserJoined()
        );
    }
}
