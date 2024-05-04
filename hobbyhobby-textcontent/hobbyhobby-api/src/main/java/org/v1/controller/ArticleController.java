package org.v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.v1.dto.ArticleRequest;
import org.v1.dto.ArticleResponse;
import org.v1.model.Article;
import org.v1.service.ArticleService;
import response.HttpResponse;

import java.util.List;

@Tag(name = "H-Board", description = "H-Board API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles")
public class ArticleController {
    private final ArticleService articleService;
    @GetMapping("/latest")
    @Operation(summary = "h-board 게시글 최신순 제목 가져오기 max 10개")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> getArticleLatest(
            @RequestBody ArticleRequest.LatestRequest articleRequest
    ) {
        List<Article> articleList = articleService.getTenArticle(articleRequest.communityId());
        return HttpResponse.success(ArticleResponse.of(articleList));
    }
    @GetMapping("/search")
    @Operation(summary = "h-board 게시글 검색 제목 가져오기 max 10개")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> getArticleSearch(
            @RequestBody ArticleRequest.SearchRequest articleRequest
    ) {
        List<Article> articleList = articleService.getTenSearchArticle(articleRequest.toSearch());
        return HttpResponse.success(ArticleResponse.of(articleList));
    }
}
