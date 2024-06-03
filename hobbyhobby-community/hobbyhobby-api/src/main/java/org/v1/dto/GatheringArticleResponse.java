package org.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.v1.model.community.Community;
import org.v1.model.content.GatheringArticle;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

record GatheringArticleResponse(
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
        @Schema(description = "첫번째 태그 이름", example = "축구")
        String communityName1,
        @Schema(description = "두번쨰 태그 커뮤니티Id", example = "2")
        Integer communityId2,
        @Schema(description = "두번쨰 태그 이름", example = "야구")
        String communityName2,
        @Schema(description = "게시물 이미지 url", example = "http://")
        String imageUrl
) {
    static GatheringArticleResponse fromGatheringArticle(GatheringArticle gatheringArticle) {
        return new GatheringArticleResponse(
                gatheringArticle.getId(),
                gatheringArticle.getCreatedAt(),
                gatheringArticle.getTitle(),
                gatheringArticle.getUser().nickname(),
                gatheringArticle.getLikes(),
                gatheringArticle.getJoinedMax(),
                gatheringArticle.getJoinedCount(),
                gatheringArticle.getCommunities().get(0).getId(),
                gatheringArticle.getCommunities().get(0).getCommunityName(),
                gatheringArticle.getCommunities().get(1).getId(),
                gatheringArticle.getCommunities().get(1).getCommunityName(),
                gatheringArticle.getImageUrl()
        );
    }
}
