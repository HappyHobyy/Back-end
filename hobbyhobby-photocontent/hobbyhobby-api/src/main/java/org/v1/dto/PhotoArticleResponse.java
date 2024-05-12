package org.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.v1.model.PhotoArticle;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record
PhotoArticleResponse(
        @Schema(description = "게시글 Id", example = "123")
        Long photoArticleId,
        @Schema(description = "게시글 날짜", example = "2024-05-06T15:23:45.123456789")
        LocalDateTime date,
        @Schema(description = "게시글 작성자 닉네임", example = "hobbyhobby")
        String nickname,
        @Schema(description = "게시글 좋아요 갯수", example = "12")
        Integer likes,
        @Schema(description = "게시글 댓글 갯수", example = "12")
        Integer comments,
        @Schema(description = "게시글 첫번쨰 사진 Url", example = "https://asdfsd.com/asdfsd")
        String url

) {
    public static List<PhotoArticleResponse> of(List<PhotoArticle> photoArticleList) {
        if (photoArticleList == null) {
            return Collections.emptyList();
        }

        return photoArticleList.stream()
                .map(photoArticle -> new PhotoArticleResponse(
                        photoArticle.getId(),
                        photoArticle.getDate(),
                        photoArticle.getUser().nickname(),
                        photoArticle.getLikesComments().likes(),
                        photoArticle.getLikesComments().comments(),
                        photoArticle.getMainImageUrl()
                ))
                .collect(Collectors.toList());
    }
}
