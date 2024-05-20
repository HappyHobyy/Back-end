package org.v1.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.v1.model.article.PhotoArticle;
import org.v1.model.imageVideo.ImageVideo;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public record PhotoArticleResponse(
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
        @Schema(description = "게시글 내용", example = "배고파")
        String text,
        List<ImageResponse> images,
        @Schema(description = "게시글 작성자 유무", example = "true")
        boolean isUserArticleOwner,
        @Schema(description = "게시글 좋아요 유무", example = "true")
        boolean isUserLiked
) {
    public record ImageResponse(
            @Schema(description = "이미지 번호", example = "2")
            Integer index,
            @Schema(description = "경로", example = "http://....")
            String path
    ) {
        public static ImageResponse of(ImageVideo imageVideo) {
            return new ImageResponse(imageVideo.index(), imageVideo.path());
        }
    }

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
                        photoArticle.getContent().getText(),
                        Optional.ofNullable(photoArticle.getContent().getImages())
                                .map(images -> images.stream().map(ImageResponse::of).toList())
                                .orElse(Collections.emptyList()),
                        Optional.of(photoArticle.getUserStatus().isUserOwner()).orElse(false),
                        Optional.of(photoArticle.getUserStatus().isUserLiked()).orElse(false)
                ))
                .collect(Collectors.toList());
    }
}
