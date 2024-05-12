package org.v1.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;
import org.v1.model.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public record ReviewArticleCommentRequest(
        Create create,
        Delete delete
) {
    public record Create(
            @Schema(description = "게시글Id", example = "123")
            @NotNull(message = "게시글Id는 필수 입력값입니다.")
            Long articleId,
            @Schema(description = "별점", example = "4.512321")
            @NotNull(message = "리뷰 별점는 필수 입력값입니다.")
            double star,
            @Schema(description = "댓글 내용", example = "testComment")
            @NotNull(message = "댓글 내용은 필수 입력값입니다.")
            String comment
    ) {
        private Comment toComment(Long userId) {
            return new Comment(User.onlyUserId(userId), LocalDateTime.now(), comment,null);
        }

        public ReviewComment toReviewComment(Content.Image image, Long userId) {
            return ReviewComment.withoutId(toComment(userId), star, image);
        }
    }

    public record Delete(
            @Schema(description = "댓글Id", example = "456")
            @NotNull(message = "댓글Id는 필수 입력값입니다.")
            Long commentId
    ) {
    }
}
