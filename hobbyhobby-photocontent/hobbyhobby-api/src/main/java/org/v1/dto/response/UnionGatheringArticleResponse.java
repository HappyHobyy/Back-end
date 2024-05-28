package org.v1.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.v1.model.article.GatheringArticle;
import org.v1.model.imageVideo.ImageVideo;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record UnionGatheringArticleResponse(
        @Schema(description = "게시글 Id", example = "123")
        long gatheringArticleId,
        @Schema(description = "게시글 날짜", example = "2024-05-06T15:23:45")
        LocalDateTime createdAt,
        @Schema(description = "게시글 제목", example = "hobbyhobby")
        String title,
        @Schema(description = "게시글 작성자 닉네임", example = "hobbyhobby")
        String userNickname,
        @Schema(description = "게시글 좋아요 갯수", example = "12")
        int likes,
        @Schema(description = "모임 유저 숫자", example = "12")
        int joinMax,
        @Schema(description = "현재 모인 유저 숫자", example = "12")
        int joinCount,
        @Schema(description = "첫번째 태그 커뮤니티Id", example = "1")
        Integer communityId1,
        @Schema(description = "두번쨰 태그 커뮤니티Id", example = "2")
        Integer communityId2,
        ImageResponse image
) {
    public record ImageResponse(
            @Schema(description = "경로", example = "http://....")
            String path
    ) {
        public static ImageResponse of(ImageVideo imageVideo) {
            return new ImageResponse(imageVideo.path());
        }
    }

    public static List<UnionGatheringArticleResponse> of(List<GatheringArticle> gatheringArticles) {
        if (gatheringArticles == null) {
            return Collections.emptyList();
        }
        return gatheringArticles.stream()
                .map(gatheringArticle -> new UnionGatheringArticleResponse(
                        gatheringArticle.getId(),
                        gatheringArticle.getCreatedAt(),
                        gatheringArticle.getTitle(),
                        gatheringArticle.getUser().nickname(),
                        gatheringArticle.getLikes(),
                        gatheringArticle.getJoinedMax(),
                        gatheringArticle.getJoinedCount(),
                        gatheringArticle.getInfo().communityIds().get(0),
                        gatheringArticle.getInfo().communityIds().get(1),
                        ImageResponse.of(gatheringArticle.getImageVideo())
                ))
                .collect(Collectors.toList());
    }
}
