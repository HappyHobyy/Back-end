package org.v1.controller.gatheringarticle;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.v1.dto.ArticleLikeRequest;
import org.v1.service.gathering.GatheringArticleLikeService;
import response.HttpResponse;

@Tag(name = "모임", description = "모임 API")
@RestController
@AllArgsConstructor
@RequestMapping("/api/gathering/like")
public class GatheringArticleLikeController {
    private final GatheringArticleLikeService likeService;
    @PostMapping("")
    @Operation(summary = "모임 게시글 좋아요 누르기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> createLikeArticle(
            @RequestBody ArticleLikeRequest articleLikeRequest,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        likeService.createArticleLike(articleLikeRequest.toGatheringArticleLike(userId));
        return HttpResponse.successOnly();
    }
    @DeleteMapping("")
    @Operation(summary = "모임 게시글 좋아요 취소하기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> deleteArticleLike(
            @RequestBody ArticleLikeRequest articleLikeRequest,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        likeService.deleteArticleLike(articleLikeRequest.toGatheringArticleLike(userId));
        return HttpResponse.successOnly();
    }
}
