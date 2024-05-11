package org.v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.v1.dto.request.TextArticleCommentRequest;
import org.v1.dto.request.TextArticleLikeRequest;
import org.v1.dto.response.TextArticleDetailResponse;
import org.v1.model.TextArticleDetail;
import org.v1.service.TextArticleDetailService;
import response.DefaultId;
import response.HttpResponse;

import java.util.List;

@Tag(name = "H-Board", description = "H-Board API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/article/text")
public class TextArticleDetailController {
    private final TextArticleDetailService textArticleDetailService;
    @GetMapping("/detail")
    @Operation(summary = "h-board 게시글 내용 가져오기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<List<TextArticleDetailResponse>> getArticle(
            @RequestBody Long articleId,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        TextArticleDetail textArticleDetail = textArticleDetailService.getArticleDetail(articleId,userId);
        return HttpResponse.success(TextArticleDetailResponse.of(textArticleDetail));
    }
    @PostMapping("/detail/like")
    @Operation(summary = "h-board 게시글 좋아요 누르기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> createLikeArticle(
            @RequestBody TextArticleLikeRequest request,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        textArticleDetailService.likeArticle(request.toLike(userId));
        return HttpResponse.successOnly();
    }
    @DeleteMapping("/detail/like")
    @Operation(summary = "h-board 게시글 좋아요 취소하기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> deleteArticleLike(
            @RequestBody TextArticleLikeRequest request,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        textArticleDetailService.unlikeArticle(request.toLike(userId));
        return HttpResponse.successOnly();
    }
    @PostMapping("/detail/comment")
    @Operation(summary = "h-board 게시글 댓글 추가")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<DefaultId> createArticleComment(
            @RequestBody TextArticleCommentRequest.Create request,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        Long commentId = textArticleDetailService.commentOnArticle(request.toComment(userId),request.articleId());
        return HttpResponse.success(DefaultId.of(commentId));
    }
    @DeleteMapping("/detail/comment")
    @Operation(summary = "h-board 게시글 댓글 삭제")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> deleteArticleComment(
            @RequestBody TextArticleCommentRequest.Delete request
    ) {
        textArticleDetailService.deleteArticleComment(request.commentId());
        return HttpResponse.successOnly();
    }
}
