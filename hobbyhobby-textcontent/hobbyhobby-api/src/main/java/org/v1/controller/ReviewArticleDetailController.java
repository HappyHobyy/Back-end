package org.v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.v1.dto.request.ReviewArticleCommentRequest;
import org.v1.dto.response.ReviewArticleDetailResponse;
import org.v1.global.util.FileUtil;
import org.v1.model.Content;
import org.v1.model.ReviewArticleDetail;
import org.v1.model.ReviewComment;
import org.v1.service.ReviewArticleDetailService;
import response.DefaultId;
import response.HttpResponse;

@Tag(name = "Review", description = "Review API")
@RestController
@AllArgsConstructor
@RequestMapping("/api/article/review")
public class ReviewArticleDetailController {
    private final ReviewArticleDetailService reviewArticleDetailService;
    private final FileUtil fileUtil;

    @GetMapping("/detail")
    @Operation(summary = "장비 리뷰 게시글 내용 가져오기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<ReviewArticleDetailResponse> getArticle(
            @RequestBody Long articleId,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        ReviewArticleDetail reviewArticleDetail = reviewArticleDetailService.getArticleDetail(articleId, userId);
        return HttpResponse.success(ReviewArticleDetailResponse.of(reviewArticleDetail));
    }

    @PostMapping("/detail/comment")
    @Operation(summary = "장비 리뷰 게시글 댓글 추가")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<DefaultId> createArticleComment(
            @RequestBody ReviewArticleCommentRequest.Create request,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId,
            @RequestPart("file") MultipartFile file
    ) {
        ReviewComment comment = request.toReviewComment(fileUtil.convertMultipartFile(file, Content.ImageType.REVIEW_COMMENT),userId);
        Long commentId = reviewArticleDetailService.commentOnArticle(comment,request.articleId());
        return HttpResponse.success(DefaultId.of(commentId));
    }

    @DeleteMapping("/detail/comment")
    @Operation(summary = "장비 리뷰 게시글 댓글 삭제")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> deleteArticleComment(
            @RequestBody ReviewArticleCommentRequest.Delete request
    ) {
        reviewArticleDetailService.deleteArticleComment(request.commentId());
        return HttpResponse.successOnly();
    }
}
