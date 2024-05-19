package org.v1.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.v1.model.imageVideo.ImageVideo;
import org.v1.model.article.PhotoArticleDetail;
import org.v1.model.comment.Comment;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public record PhotoArticleDetailResponse(
        String text,
        List<ImageResponse> images,
        List<CommentResponse> comments,
        boolean isUserArticleOwner,
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

    public record CommentResponse(
            @Schema(description = "댓글 Id", example = "123")
            Long commentId,
            @Schema(description = "유저닉네임", example = "hobbyhobby")
            String userNickname,
            @Schema(description = "댓글 시간", example = "2024-05-06T15:23:45.123456789")
            LocalDateTime date,
            @Schema(description = "댓글 내용", example = "hobbyasdfknsadf")
            String text,
            @Schema(description = "댓글 작성자 보인 유무", example = "true/false")
            boolean isUserCommentOwner
    ) {
        public static CommentResponse of(Comment comment) {
            return new CommentResponse(comment.getId(), comment.getUser().nickname(), comment.getDate(), comment.getText(), comment.getUserStatus().isUserOwner());
        }
    }

    public static List<PhotoArticleDetailResponse> of(PhotoArticleDetail photoArticleDetail) {
        return Optional.ofNullable(photoArticleDetail)
                .map(detail -> new PhotoArticleDetailResponse(
                        photoArticleDetail.getPhotoAriticleContent().getText(),
                        Optional.ofNullable(detail.getPhotoAriticleContent().getImages())
                                .map(images -> images.stream().map(ImageResponse::of).toList())
                                .orElse(Collections.emptyList()),
                        Optional.ofNullable(detail.getComments())
                                .map(comments -> comments.stream().map(CommentResponse::of).toList())
                                .orElse(Collections.emptyList()),
                        Optional.of(detail.getUserStatus().isUserOwner()).orElse(false),
                        Optional.of(detail.getUserStatus().isUserLiked()).orElse(false)
                ))
                .map(List::of)
                .orElse(Collections.emptyList());
    }
}
