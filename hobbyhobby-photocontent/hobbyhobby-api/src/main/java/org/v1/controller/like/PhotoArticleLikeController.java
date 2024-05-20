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

@Tag(name = "H-Log", description = "H-Log API")
@RestController
@AllArgsConstructor
@RequestMapping("/api/hlog/like")
public class PhotoArticleLikeController {
    private final ArticleLikeService likeService;
    @PostMapping("")
    @Operation(summary = "H-log 게시글 좋아요 누르기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> createLikeArticle(
            @RequestBody ArticleLikeRequest request,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        likeService.createArticleLike(request.toPhoto(userId));
        return HttpResponse.successOnly();
    }
    @DeleteMapping("")
    @Operation(summary = "H-log 게시글 좋아요 취소하기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> deleteArticleLike(
            @RequestBody ArticleLikeRequest request,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        likeService.deleteArticleLike(request.toPhoto(userId));
        return HttpResponse.successOnly();
    }
}
