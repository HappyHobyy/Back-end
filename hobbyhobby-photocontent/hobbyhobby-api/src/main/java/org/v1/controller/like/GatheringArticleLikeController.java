package org.v1.controller.like;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.v1.dto.request.ArticleLikeRequest;
import org.v1.service.like.ArticleLikeService;
import response.HttpResponse;

@Tag(name = "모임", description = "모임 API")
@RestController
@AllArgsConstructor
@RequestMapping("/api/gathering")
public class GatheringArticleLikeController {
    private final ArticleLikeService likeService;
    @PostMapping("/single/like")
    @Operation(summary = "단일 모임 게시글 좋아요 누르기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> createSingleArticleLike(
            @RequestBody ArticleLikeRequest request,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        likeService.createArticleLike(request.toSingle(userId));
        return HttpResponse.successOnly();
    }
    @DeleteMapping("/single/like")
    @Operation(summary = "단일 모임 게시글 좋아요 취소하기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> deleteSingleArticleLike(
            @RequestBody ArticleLikeRequest request,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        likeService.deleteArticleLike(request.toSingle(userId));
        return HttpResponse.successOnly();
    }
    @PostMapping("/multi/like")
    @Operation(summary = "연합 모임 게시글 좋아요 누르기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> createUnionArticleLike(
            @RequestBody ArticleLikeRequest request,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        likeService.createArticleLike(request.toUnion(userId));
        return HttpResponse.successOnly();
    }
    @DeleteMapping("/multi/like")
    @Operation(summary = "연합 모임 게시글 좋아요 취소하기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> deleteUnionArticleLike(
            @RequestBody ArticleLikeRequest request,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        likeService.deleteArticleLike(request.toUnion(userId));
        return HttpResponse.successOnly();
    }


}
