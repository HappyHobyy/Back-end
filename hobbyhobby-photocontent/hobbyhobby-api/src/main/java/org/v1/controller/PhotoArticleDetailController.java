package org.v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.v1.dto.PhotoArticleCommentRequest;
import org.v1.dto.PhotoArticleDetailResponse;
import org.v1.dto.PhotoArticleLikeRequest;
import org.v1.model.PhotoArticleDetail;
import org.v1.service.PhotoArticleDetailService;
import response.DefaultId;
import response.HttpResponse;

import java.util.List;

@Tag(name = "H-Board", description = "H-Board API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/article")
public class PhotoArticleDetailController {

    private final PhotoArticleDetailService photoArticleDetailService;
    @GetMapping("/detail")
    @Operation(summary = "H-log 게시글 내용 가져오기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<List<PhotoArticleDetailResponse>> getArticle(
            @RequestBody Long photoArticleId,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        PhotoArticleDetail photoArticleDetail = photoArticleDetailService.getArticleDetail(photoArticleId,userId);
        return HttpResponse.success(PhotoArticleDetailResponse.of(photoArticleDetail));
    }
    @PostMapping("/detail/like")
    @Operation(summary = "H-log 게시글 좋아요 누르기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> createLikeArticle(
            @RequestBody PhotoArticleLikeRequest photoArticleLikeRequest,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        photoArticleDetailService.createArticleLike(photoArticleLikeRequest.toLike(userId));
        return HttpResponse.successOnly();
    }
    @DeleteMapping("/detail/like")
    @Operation(summary = "H-log 게시글 좋아요 취소하기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> deleteArticleLike(
            @RequestBody PhotoArticleLikeRequest photoArticleLikeRequest,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        photoArticleDetailService.deleteArticleLike(photoArticleLikeRequest.toLike(userId));
        return HttpResponse.successOnly();
    }
    @PostMapping("/detail/comment")
    @Operation(summary = "H-log 게시글 댓글 추가")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<DefaultId> createArticleComment(
            @RequestBody PhotoArticleCommentRequest.Create articleCommentRequest,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId,
            @Parameter(hidden = true) @Valid @RequestHeader String nickname
    ) {
        Long commentId = photoArticleDetailService.createArticleComment(articleCommentRequest.toComment(userId),articleCommentRequest.photoArticleId());
        return HttpResponse.success(DefaultId.of(commentId));
    }
    @DeleteMapping("/detail/comment")
    @Operation(summary = "H-log 게시글 댓글 삭제")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> deleteArticleComment(
            @RequestBody PhotoArticleCommentRequest.Delete articleCommentRequest
    ) {
        photoArticleDetailService.deleteArticleComment(articleCommentRequest.commentId());
        return HttpResponse.successOnly();
    }
}
