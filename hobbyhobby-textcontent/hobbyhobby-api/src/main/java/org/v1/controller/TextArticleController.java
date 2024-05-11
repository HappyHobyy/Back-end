package org.v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.v1.dto.request.TextArticleRequest;
import org.v1.dto.response.TextArticleResponse;
import org.v1.global.util.FileUtil;
import org.v1.model.TextArticle;
import org.v1.service.TextArticleService;
import response.DefaultId;
import response.HttpResponse;

import java.util.List;

@Tag(name = "H-Board", description = "H-Board API")
@RestController
@AllArgsConstructor
@RequestMapping("/api/article/text")
public class TextArticleController {
    private final TextArticleService textArticleService;
    private final FileUtil fileUtil;
    @GetMapping("/latest")
    @Operation(summary = "h-board 게시글 최신순 제목 가져오기 max 10개")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> getArticleLatest(
            @RequestBody TextArticleRequest.LatestRequest request
    ) {
        List<TextArticle> articleList = textArticleService.getRecentTextArticles(request.communityId());
        return HttpResponse.success(TextArticleResponse.of(articleList));
    }
    @GetMapping("/search")
    @Operation(summary = "h-board 게시글 검색 제목 가져오기 max 10개")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> getArticleSearch(
            @RequestBody TextArticleRequest.SearchRequest request
    ) {
        List<TextArticle> articleList = textArticleService.getRecentTextArticlesBySearch(request.toSearch());
        return HttpResponse.success(TextArticleResponse.of(articleList));
    }
    @DeleteMapping("")
    @Operation(summary = "h-board 게시글 삭제")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> deleteArticle(
            @RequestBody TextArticleRequest.DeleteRequest request
    ) {
        textArticleService.deleteTextArticle(request.articleId());
        return HttpResponse.successOnly();
    }
    @PostMapping("")
    @Operation(summary = "h-board 게시글 저장")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> createArticle(
            @RequestPart TextArticleRequest.CreateRequest request,
            @RequestPart("files") List<MultipartFile> files,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        Long articleId = textArticleService.createTextArticle(request.toArticle(userId),request.toContent(fileUtil.convertMultipartFiles(files)));
        return HttpResponse.success(DefaultId.of(articleId));
    }
}
