package org.v1.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.v1.model.ReviewArticle;
import org.v1.model.TextArticle;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record ReviewArticleResponse(
        @Schema(description = "게시글 Id", example = "123")
        Long articleId,
        @Schema(description = "게시글 제목", example = "testTitle")
        String title,
        @Schema(description = "게시글 날짜", example = "2024-05-06T15:23:45.123456789")
        LocalDateTime date,
        @Schema(description = "게시글 작성자 닉네임", example = "hobbyhobby")
        String nickname,
        @Schema(description = "장비 평점", example = "12")
        double star,
        @Schema(description = "게시글 댓글 갯수", example = "12")
        Integer comments,
        @Schema(description = "첫번째 이미지 경로", example = "http://123")
        String imageUrl
) {
    public static List<ReviewArticleResponse> of(List<ReviewArticle> articleList) {
        if (articleList == null) {
            return Collections.emptyList();
        }
        return articleList.stream()
                .map(reviewArticle -> new ReviewArticleResponse(
                        reviewArticle.getId(),
                        reviewArticle.getArticle().title(),
                        reviewArticle.getArticle().date(),
                        reviewArticle.getArticle().user().nickname(),
                        reviewArticle.getStars(),
                        reviewArticle.getArticle().comments(),
                        reviewArticle.getImage().path()
                ))
                .collect(Collectors.toList());
    }
}