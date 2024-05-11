package org.v1.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.v1.model.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record ReviewArticleDetailResponse(
        @Schema(description = "텍스트", example = "hobbyhobby..")
        String text,
        @Schema(description = "이미지 경로", example = "http://....")
        String imagePath,
        List<CommentResponse> comments,
        @Schema(description = "작성자 본인 유무", example = "true/false")
        boolean isUserArticleOwner
) {
    public record CommentResponse(
            @Schema(description = "댓글 Id", example = "123")
            Long commentId,
            @Schema(description = "유저닉네임", example = "hobbyhobby")
            String userNickname,
            @Schema(description = "댓글 시간", example = "2024-05-06T15:23:45.123456789")
            LocalDateTime date,
            @Schema(description = "댓글 작성자 보인 유무", example = "true/false")
            boolean isUserCommentOwner,
            @Schema(description = "텍스트", example = "hobbyhobby..")
            String text,
            @Schema(description = "경로", example = "http://....")
            String path,
            @Schema(description = "평점", example = "4.5123")
            double star

    ) {
        public static CommentResponse of(ReviewComment comment) {
            return new CommentResponse(
                    comment.getId(),
                    comment.getComment().getUser().nickname(),
                    comment.getComment().getDate(),
                    comment.getComment().getUserStatus().isUserArticleOwner(),
                    comment.getComment().getText(),
                    comment.getImage()
                            .map(Content.Image::path)
                            .orElse(null),
                    comment.getStars()
            );
        }
    }

    public static ReviewArticleDetailResponse of(ReviewArticleDetail reviewArticleDetail) {
        if (reviewArticleDetail == null) {
            return null;
        }
        return new ReviewArticleDetailResponse(
                reviewArticleDetail.getReviewContent().getText()
                        .map(Content.Text::text)
                        .orElse(null),
                reviewArticleDetail.getReviewContent().getImage()
                        .map(Content.Image::path)
                        .orElse(null),
                reviewArticleDetail.getComments().stream()
                        .map(CommentResponse::of)
                        .collect(Collectors.toList()),
                reviewArticleDetail.getUserStatus().isUserArticleOwner()
        );
    }
}
