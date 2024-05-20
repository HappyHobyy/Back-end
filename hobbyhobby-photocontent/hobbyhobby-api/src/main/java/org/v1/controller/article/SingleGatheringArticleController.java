package org.v1.controller.article;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.v1.dto.request.SingleGatheringArticleRequest;
import org.v1.model.article.ArticleType;
import org.v1.model.article.GatheringArticleDetail;
import org.v1.service.article.GatheringArticleService;
import response.DefaultId;
import response.HttpResponse;

import java.util.List;

@Tag(name = "모임", description = "모임 API")
@RestController
@AllArgsConstructor
@RequestMapping("/api/gathering/single")
public class SingleGatheringArticleController {
    private final GatheringArticleService service;

    @GetMapping("")
    @Operation(summary = "단일 모임 게시글 최신순 제목 가져오기 max 10개")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> getSingleGatheringLatest(
    ) {
        service.getTenArticleLatest(ArticleType.SINGLE_GATHERING);
        return HttpResponse.successOnly();
    }

    @GetMapping("/search")
    @Operation(summary = "단일 모임 게시글 검색 제목 가져오기 max 10개")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> getSingleGatheringSearch(
            @RequestBody SingleGatheringArticleRequest.Latest request
    ) {
        service.getTenArticleSearch(request.toGatheringInfo());
        return HttpResponse.successOnly();
    }
    @DeleteMapping("")
    @Operation(summary = "단일 모임 게시글 삭제")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> deleteArticle(
            @RequestBody SingleGatheringArticleRequest.Delete request
    ) {
        service.deleteArticle(request.toGatheringInfo());
        return HttpResponse.successOnly();
    }

    @PostMapping("")
    @Operation(summary = "단일 모임 게시글 저장")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> createArticle(
            @RequestPart SingleGatheringArticleRequest.Create request,
            @RequestPart("file") MultipartFile file,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        Long articleId = service.createArticle(request.toArticle(userId), request.toContent(file));
        return HttpResponse.success(DefaultId.of(articleId));
    }

    @GetMapping("/detail")
    @Operation(summary = "단일 모임 게시글 내용 가져오기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> getArticle(
            @RequestBody SingleGatheringArticleRequest.Detail request,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        GatheringArticleDetail detail = service.getArticleDetail(request.toGatheringInfo(), userId);
        return HttpResponse.successOnly();
    }
}