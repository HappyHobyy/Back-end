package org.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.v1.model.Article;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record ArticleResponse(
        @Schema(description = "게시글 Id", example = "123")
        Long articleId,
        @Schema(description = "게시글 제목", example = "testTitle")
        String title,
        @Schema(description = "게시글 날짜", example = "2024-05-06T15:23:45.123456789")
        LocalDateTime date,
        @Schema(description = "게시글 작성자 닉네임", example = "hobbyhobby")
        String nickname,
        @Schema(description = "게시글 좋아요 갯수", example = "12")
        Integer likes
) {
    public static List<ArticleResponse> of(List<Article> articleList) {
        if (articleList == null) {
            return Collections.emptyList();
        }

        return articleList.stream()
                .map(textContent -> new ArticleResponse(
                        textContent.getId(),
                        textContent.getTitle(),
                        textContent.getDate(),
                        textContent.getUser().nickname(),
                        textContent.getLikes()
                ))
                .collect(Collectors.toList());
    }
}
