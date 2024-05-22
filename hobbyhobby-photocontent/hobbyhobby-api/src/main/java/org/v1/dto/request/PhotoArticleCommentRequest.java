package org.v1.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.v1.model.comment.Comment;
import org.v1.model.user.User;

import java.time.LocalDateTime;

public record PhotoArticleCommentRequest(
        Create create,
        Delete delete
) {
    public record Create(
            @Schema(description = "게시글Id", example = "123")
            @NotNull(message = "게시글Id는 필수 입력값입니다.")
            Long photoArticleId,
            @Schema(description = "댓글 내용", example = "testComment")
            @NotNull(message = "댓글 내용은 필수 입력값입니다.")
            String comment
    ) {
        public Comment toComment(Long userId) {
            return Comment.initial(User.onlyUserId(userId), LocalDateTime.now(), comment);
        }
    }
    public record Delete(Long commentId) {}
}
