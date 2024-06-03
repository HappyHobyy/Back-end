package org.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.v1.model.content.PhotoArticle;

import java.time.LocalDateTime;

record PhotoArticleResponse(
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
        @Schema(description = "댓글 수", example = "1")
        Integer comments,
        @Schema(description = "게시물 첫번째 이미지 수", example = "1")
        String firstImageUrl,
        @Schema(description = "게시물 내용", example = "밥")
        String content,
        @Schema(description = "커뮤니티id", example = "1")
        Integer communityId,
        @Schema(description = "커뮤니티id 이름", example = "야구")
        String communityName
) {
    static PhotoArticleResponse fromPhotoArticle(PhotoArticle photoArticle) {
        return new PhotoArticleResponse(
                photoArticle.getId(),
                photoArticle.getDate(),
                photoArticle.getUser().id(),
                photoArticle.getUser().nickname(),
                photoArticle.getUser().imageUrl(),
                photoArticle.getLikes(),
                photoArticle.getComments(),
                photoArticle.getFirstImageUrl(),
                photoArticle.getContent(),
                photoArticle.getCommunity().getId(),
                photoArticle.getCommunity().getCommunityName()
        );
    }
}