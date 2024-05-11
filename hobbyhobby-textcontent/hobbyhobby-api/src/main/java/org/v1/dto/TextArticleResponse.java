package org.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.v1.model.Article;
import org.v1.model.TextArticle;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record TextArticleResponse(
        @Schema(description = "게시글 Id", example = "123")
        Long articleId,
        @Schema(description = "게시글 제목", example = "testTitle")
        String title,
        @Schema(description = "게시글 날짜", example = "2024-05-06T15:23:45.123456789")
        LocalDateTime date,
        @Schema(description = "게시글 작성자 닉네임", example = "hobbyhobby")
        String nickname,
        @Schema(description = "게시글 좋아요 갯수", example = "12")
        Integer likes,
        @Schema(description = "게시글 댓글 갯수", example = "12")
        Integer comments
) {
    public static List<TextArticleResponse> of(List<TextArticle> articleList) {
        if (articleList == null) {
            return Collections.emptyList();
        }

        return articleList.stream()
                .map(textContent -> new TextArticleResponse(
                        textContent.getId(),
                        textContent.getArticle().title(),
                        textContent.getArticle().date(),
                        textContent.getArticle().user().nickname(),
                        textContent.getLikes(),
                        textContent.getArticle().comments()
                ))
                .collect(Collectors.toList());
    }
}
