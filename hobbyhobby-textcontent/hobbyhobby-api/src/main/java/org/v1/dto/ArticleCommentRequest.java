package org.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.v1.model.Comment;
import org.v1.model.User;

import java.time.LocalDateTime;

public record ArticleCommentRequest(
        Create create,
        Delete delete
) {
    public record Create(
            @Schema(description = "게시글Id", example = "123")
            @NotNull(message = "게시글Id는 필수 입력값입니다.")
            Long articleId,
            @Schema(description = "댓글 내용", example = "testComment")
            @NotNull(message = "댓글 내용은 필수 입력값입니다.")
            String comment
    ) {
        public Comment toComment(Long userId,String nickname) {
            return Comment.withoutId(new User(userId,nickname), LocalDateTime.now(), comment, false);
        }
    }
    public record Delete(Long commentId) {}
}
