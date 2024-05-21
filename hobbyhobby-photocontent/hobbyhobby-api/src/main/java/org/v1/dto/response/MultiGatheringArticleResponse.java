package org.v1.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.v1.model.article.GatheringArticle;
import org.v1.model.imageVideo.ImageVideo;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record MultiGatheringArticleResponse(
        @Schema(description = "게시글 Id", example = "123")
        long gatheringArticleId,
        @Schema(description = "게시글 날짜", example = "2024-05-06T15:23:45.123456789")
        LocalDateTime articleDate,
        @Schema(description = "게시글 작성자 닉네임", example = "hobbyhobby")
        String nickname,
        @Schema(description = "게시글 좋아요 갯수", example = "12")
        int likes,
        @Schema(description = "모임 유저 숫자", example = "12")
        int users,
        @Schema(description = "커뮤니티Id", example = "12")
        List<Integer> communityId,
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

    public static List<MultiGatheringArticleResponse> of(List<GatheringArticle> gatheringArticles) {
        if (gatheringArticles == null) {
            return Collections.emptyList();
        }
        return gatheringArticles.stream()
                .map(gatheringArticle -> new MultiGatheringArticleResponse(
                        gatheringArticle.getId(),
                        gatheringArticle.getDate(),
                        gatheringArticle.getUser().nickname(),
                        gatheringArticle.getLikes(),
                        gatheringArticle.getCountUsers(),
                        gatheringArticle.getInfo().communityIds().stream().toList(),
                        ImageResponse.of(gatheringArticle.getImageVideo())
                ))
                .collect(Collectors.toList());
    }
}
