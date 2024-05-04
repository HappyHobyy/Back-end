package org.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.v1.model.ArticleDetail;
import org.v1.model.Comment;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public record ArticleDetailResponse(
        List<TextResponse> text,
        List<ImageResponse> images,
        List<CommentResponse> comments,
        boolean isUserArticleOwner,
        boolean isUserLiked
) {
    public record TextResponse(
            @Schema(description = "텍스트 번호", example = "1")
            Integer index,
            @Schema(description = "텍스트", example = "hobbyhobby..")
            String text
    ) {
        public static TextResponse of(ArticleDetail.Text text) {
            return new TextResponse(text.index(), text.text());
        }
    }

    public record ImageResponse(
            @Schema(description = "이미지 번호", example = "2")
            Integer index,
            @Schema(description = "경로", example = "http://....")
            String path
    ) {
        public static ImageResponse of(ArticleDetail.Image image) {
            return new ImageResponse(image.index(), image.path());
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
            return new CommentResponse(comment.id(), comment.user().nickname(), comment.date(), comment.text(), comment.user().id().equals(comment.user().id()));
        }
    }

    public static List<ArticleDetailResponse> of(ArticleDetail articleDetail) {
        return Optional.ofNullable(articleDetail)
                .map(detail -> new ArticleDetailResponse(
                        Optional.ofNullable(detail.getTexts())
                                .map(texts -> texts.stream().map(TextResponse::of).toList())
                                .orElse(Collections.emptyList()),
                        Optional.ofNullable(detail.getImages())
                                .map(images -> images.stream().map(ImageResponse::of).toList())
                                .orElse(Collections.emptyList()),
                        Optional.ofNullable(detail.getComments())
                                .map(comments -> comments.stream().map(CommentResponse::of).toList())
                                .orElse(Collections.emptyList()),
                        Optional.of(detail.getUserStatus().isUserArticleOwner()).orElse(false),
                        Optional.of(detail.getUserStatus().isUserLiked()).orElse(false)
                ))
                .map(List::of)
                .orElse(Collections.emptyList());
    }
}
