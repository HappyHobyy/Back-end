package org.v1.controller.photoarticle;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.v1.dto.PhotoArticleCommentRequest;
import org.v1.service.photoarticle.PhotoArticleCommentService;
import response.DefaultId;
import response.HttpResponse;

@Tag(name = "H-Log", description = "H-Log API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/article/comment")
public class PhotoArticleCommentController {
    private final PhotoArticleCommentService commentService;
    @PostMapping("")
    @Operation(summary = "H-Log 게시글 댓글 추가")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<DefaultId> createArticleComment(
            @RequestBody PhotoArticleCommentRequest.Create articleCommentRequest,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId,
            @Parameter(hidden = true) @Valid @RequestHeader String nickname
    ) {
        Long commentId = commentService.createArticleComment(articleCommentRequest.toComment(userId),articleCommentRequest.photoArticleId());
        return HttpResponse.success(DefaultId.of(commentId));
    }
    @DeleteMapping("")
    @Operation(summary = "H-log 게시글 댓글 삭제")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> deleteArticleComment(
            @RequestBody PhotoArticleCommentRequest.Delete articleCommentRequest
    ) {
        commentService.deleteArticleComment(articleCommentRequest.commentId());
        return HttpResponse.successOnly();
    }
}
