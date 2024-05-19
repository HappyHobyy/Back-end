package org.v1.controller.photoarticle;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.v1.dto.PhotoArticleLikeRequest;
import org.v1.service.photoarticle.PhotoArticleLikeService;
import response.HttpResponse;

@Tag(name = "H-Log", description = "H-Log API")
@RestController
@AllArgsConstructor
@RequestMapping("/api/article/like")
public class PhotoArticleLikeController {
    private final PhotoArticleLikeService likeService;
    @PostMapping("")
    @Operation(summary = "H-log 게시글 좋아요 누르기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> createLikeArticle(
            @RequestBody PhotoArticleLikeRequest photoArticleLikeRequest,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        likeService.createArticleLike(photoArticleLikeRequest.toLike(userId));
        return HttpResponse.successOnly();
    }
    @DeleteMapping("")
    @Operation(summary = "H-log 게시글 좋아요 취소하기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> deleteArticleLike(
            @RequestBody PhotoArticleLikeRequest photoArticleLikeRequest,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        likeService.deleteArticleLike(photoArticleLikeRequest.toLike(userId));
        return HttpResponse.successOnly();
    }
}
