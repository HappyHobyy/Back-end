package org.v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.v1.dto.ArticleRequest;
import org.v1.dto.ArticleResponse;
import org.v1.model.Article;
import org.v1.model.Content;
import org.v1.service.ArticleService;
import response.HttpResponse;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.v1.global.util.FileUtil.convertMultipartFileToFile;

@Tag(name = "H-Board", description = "H-Board API")
@RestController
@AllArgsConstructor
@RequestMapping("/api/article")
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
    @PostMapping("/detail")
    @Operation(summary = "h-board 게시글 저장")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> createArticle(
            @RequestPart ArticleRequest.CreateRequest articleRequest,
            @RequestPart("files") List<MultipartFile> files,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId,
            @Parameter(hidden = true) @Valid @RequestHeader String nickname
    ) {
        articleService.createArticle(articleRequest.toArticle(userId,nickname),articleRequest.toContent(files));
        return HttpResponse.successOnly();
    }
    @PostMapping("/detail/test")
    @Operation(summary = "h-board 게시글 저장 테스트")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> createArticle(
            @RequestPart("files") List<MultipartFile> files
    ) {
        List<File> fileList = files.stream()
                .map(file -> {
                    try {
                        return convertMultipartFileToFile(file);
                    } catch (IOException e) {
                        throw new RuntimeException("MultipartFile -> File로 전환이 실패했습니다.",e);
                    }
                })
                .toList();
        articleService.createImage(fileList.get(0));
        return HttpResponse.successOnly();
    }
}
