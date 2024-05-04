package org.v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.v1.dto.ArticleDetailResponse;
import org.v1.model.ArticleDetail;
import org.v1.model.Like;
import org.v1.service.ArticleDetailService;
import response.HttpResponse;

@Tag(name = "H-Board", description = "H-Board API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/article")
public class ArticleDetailController {

    private final ArticleDetailService articleDetailService;
    @GetMapping("/detail")
    @Operation(summary = "h-board 게시글 내용 가져오기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> getArticle(
            @RequestBody Long articleId,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        ArticleDetail articleDetail = articleDetailService.getArticleDetail(articleId);
        return HttpResponse.success(ArticleDetailResponse.of(articleDetail));
    }
    @PostMapping("/detail/like")
    @Operation(summary = "h-board 게시글 좋아요 누르기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> PostLikeArticle(
            @RequestBody Long articleId,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        articleDetailService.postArticleLike(new Like(articleId,userId));
        return HttpResponse.successOnly();
    }
    @DeleteMapping("/detail/like")
    @Operation(summary = "h-board 게시글 좋아요 취소하기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> DeleteArticleLike(
            @RequestBody Long articleId,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        articleDetailService.postArticleLike(new Like(articleId,userId));
        return HttpResponse.successOnly();
    }
}
