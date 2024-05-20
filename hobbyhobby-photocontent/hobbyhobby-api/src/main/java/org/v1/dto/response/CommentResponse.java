package org.v1.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.v1.model.imageVideo.ImageVideo;
import org.v1.model.comment.Comment;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


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
    public static List<CommentResponse> of(List<Comment> comments) {
        return comments.stream()
                .map(comment -> new CommentResponse(
                        comment.getId(),
                        comment.getUser().nickname(),
                        comment.getDate(),
                        comment.getText(),
                        comment.getUserStatus().isUserOwner()
                ))
                .toList();
    }
}
