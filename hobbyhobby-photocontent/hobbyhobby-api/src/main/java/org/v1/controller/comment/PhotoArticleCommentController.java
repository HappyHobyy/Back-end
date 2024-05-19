package org.v1.controller.comment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.v1.dto.request.PhotoArticleCommentRequest;
import org.v1.service.like.ArticleCommentService;
import response.DefaultId;
import response.HttpResponse;

@Tag(name = "H-Log", description = "H-Log API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hlog/comment")
public class PhotoArticleCommentController {
    private final ArticleCommentService commentService;
    @PostMapping("")
    @Operation(summary = "H-Log 게시글 댓글 추가")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<DefaultId> createArticleComment(
            @RequestBody PhotoArticleCommentRequest.Create request,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        Long commentId = commentService.createArticleComment(request.toComment(userId),request.photoArticleId());
        return HttpResponse.success(DefaultId.of(commentId));
    }
    @DeleteMapping("")
    @Operation(summary = "H-log 게시글 댓글 삭제")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> deleteArticleComment(
            @RequestBody PhotoArticleCommentRequest.Delete request
    ) {
        commentService.deleteArticleComment(request.commentId());
        return HttpResponse.successOnly();
    }
}
