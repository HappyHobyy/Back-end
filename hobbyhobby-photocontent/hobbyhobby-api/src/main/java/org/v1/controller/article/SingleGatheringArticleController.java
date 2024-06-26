package org.v1.controller.article;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.v1.dto.request.SingleGatheringArticleRequest;
import org.v1.dto.response.GatheringArticleDetailResponse;
import org.v1.dto.response.SingleGatheringArticleResponse;
import org.v1.model.article.ArticleType;
import org.v1.model.article.GatheringArticle;
import org.v1.model.article.GatheringArticleDetail;
import org.v1.model.article.GatheringInfo;
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
    @Operation(summary = "단일 모임 게시글 최신순 가져오기 max 10개")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<List<SingleGatheringArticleResponse>> getSingleGatheringLatest(
            @Schema(description = "게시물 index", example = "0")
            @RequestHeader Integer index
    ) {
        List<GatheringArticle> articleList = service.getTenArticleLatest(index, ArticleType.SINGLE_GATHERING);
        return HttpResponse.success(SingleGatheringArticleResponse.of(articleList));
    }

    @GetMapping("/search")
    @Operation(summary = "단일 모임 게시글 검색 가져오기 max 10개")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<List<SingleGatheringArticleResponse>> getSingleGatheringSearch(
            @Schema(description = "게시물 index", example = "0")
            @RequestHeader Integer index,
            @Schema(description = "커뮤니티Id", example = "123")
            @RequestHeader Integer communityId
    ) {
        List<GatheringArticle> articleList = service.getTenArticleSearch(index, GatheringInfo.singleGatheringWithCommunity(communityId));
        return HttpResponse.success(SingleGatheringArticleResponse.of(articleList));
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
    public HttpResponse<DefaultId> createArticle(
            @RequestPart SingleGatheringArticleRequest.Create request,
            @RequestPart("file") MultipartFile file,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        Long articleId = service.createArticle(request.toArticle(userId,file), request.toContent());
        return HttpResponse.success(DefaultId.of(articleId));
    }

    @GetMapping("/detail")
    @Operation(summary = "단일 모임 게시글 세부 내용 가져오기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<GatheringArticleDetailResponse> getArticle(
            @Schema(description = "게시물 id", example = "1")
            @RequestHeader Long articleId,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        GatheringArticleDetail detail = service.getArticleDetail(GatheringInfo.singleGatheringWithArticle(articleId), userId);
        return HttpResponse.success(GatheringArticleDetailResponse.of(detail));
    }
}