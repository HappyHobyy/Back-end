package org.v1.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.v1.model.Comment;
import org.v1.model.TextComment;
import org.v1.model.User;
import org.v1.model.UserStatus;

import java.time.LocalDateTime;

public record TextArticleCommentRequest(Create create, Delete delete) {
    public record Create(
            @Schema(description = "게시글Id", example = "123")
            @NotNull(message = "게시글Id는 필수 입력값입니다.")
            Long articleId,
            @Schema(description = "댓글 내용", example = "testComment")
            @NotNull(message = "댓글 내용은 필수 입력값입니다.")
            String comment
    ) {
        public TextComment toComment(Long userId) {
            return TextComment.withoutId(new Comment(User.onlyUserId(userId), LocalDateTime.now(),UserStatus.onlyIsUserArticleOwner(false)),comment);
        }
    }

    public record Delete(
            @Schema(description = "댓글Id", example = "456")
            @NotNull(message = "댓글Id는 필수 입력값입니다.")
            Long commentId
    ) {}
}