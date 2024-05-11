package org.v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.v1.dto.request.ReviewArticleRequest;
import org.v1.dto.request.TextArticleRequest;
import org.v1.dto.response.ReviewArticleResponse;
import org.v1.dto.response.TextArticleResponse;
import org.v1.global.util.FileUtil;
import org.v1.model.ReviewArticle;
import org.v1.model.TextArticle;
import org.v1.service.ReviewArticleService;
import response.DefaultId;
import response.HttpResponse;

import java.util.List;

@Tag(name = "Review", description = "Review API")
@RestController
@AllArgsConstructor
@RequestMapping("/api/article/review")
public class ReviewArticleController {
    private final ReviewArticleService reviewArticleService;
    private final FileUtil fileUtil;
    @GetMapping("/latest")
    @Operation(summary = "장비 리뷰 게시글 최신순 제목 가져오기 max 10개")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> getArticleLatest(
            @RequestBody ReviewArticleRequest.LatestRequest request
    ) {
        List<ReviewArticle> articleList = reviewArticleService.getRecentTextArticles(request.communityId());
        return HttpResponse.success(ReviewArticleResponse.of(articleList));
    }
    @DeleteMapping("")
    @Operation(summary = "장비 리뷰 게시글 삭제")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> deleteArticle(
            @RequestBody ReviewArticleRequest.DeleteRequest request
    ) {
        reviewArticleService.deleteReviewArticle(request.articleId());
        return HttpResponse.successOnly();
    }
    @PostMapping("")
    @Operation(summary = "장비 리뷰 게시글 저장")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> createArticle(
            @RequestPart ReviewArticleRequest.CreateRequest request,
            @RequestPart("files") List<MultipartFile> files,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        Long articleId = reviewArticleService.createReviewArticle(request.toArticle(userId),request.toContent(fileUtil.convertMultipartFiles(files)));
        return HttpResponse.success(DefaultId.of(articleId));
    }
}
