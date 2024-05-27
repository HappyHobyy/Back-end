package org.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.v1.model.community.Community;
import org.v1.model.content.GatheringArticle;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

record GatheringArticleResponse(
        @Schema(description = "게시글 id", example = "1")
        Long articleId,
        @Schema(description = "작성일", example = "DateTime")
        LocalDateTime date,
        @Schema(description = "작성자 id", example = "1")
        Long userId,
        @Schema(description = "작성자 이름", example = "철수")
        String userNickName,
        @Schema(description = "작성자 사진 url", example = "http://example.com")
        String userImagePath,
        @Schema(description = "좋아요 수", example = "1")
        Integer likes,
        @Schema(description = "모임원 수", example = "12")
        Integer countUsers,
        @Schema(description = "이미지 url", example = "http://")
        String imageUrl,
        List<CommunityResponse> communities
) {
    public record CommunityResponse(
            @Schema(description = "커뮤니티id", example = "1")
            Integer communityId,
            @Schema(description = "커뮤니티id 이름", example = "야구")
            String communityName
    ) {
        public static List<CommunityResponse> of(List<Community> communities) {
            return communities.stream()
                    .map(community -> new CommunityResponse(community.getId(), community.getCommunityName()))
                    .collect(Collectors.toList());
        }
    }

    static GatheringArticleResponse fromGatheringArticle(GatheringArticle gatheringArticle) {
        return new GatheringArticleResponse(
                gatheringArticle.getId(),
                gatheringArticle.getDate(),
                gatheringArticle.getUser().id(),
                gatheringArticle.getUser().nickname(),
                gatheringArticle.getUser().path(),
                gatheringArticle.getLikes(),
                gatheringArticle.getCountUsers(),
                gatheringArticle.getFirstImageUrl(),
                CommunityResponse.of(gatheringArticle.getCommunities())
        );
    }
}
