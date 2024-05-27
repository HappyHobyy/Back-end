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
import org.v1.dto.response.CommentResponse;
import org.v1.dto.request.PhotoArticleRequest;
import org.v1.dto.response.PhotoArticleResponse;
import org.v1.model.article.PhotoArticle;
import org.v1.model.comment.Comment;
import org.v1.service.article.PhotoArticleService;
import response.DefaultId;
import response.HttpResponse;

import java.util.List;

@Tag(name = "H-Log", description = "H-Log API")
@RestController
@AllArgsConstructor
@RequestMapping("/api/hlog")
public class PhotoArticleController {
    private final PhotoArticleService photoArticleService;

    @GetMapping("/latest")
    @Operation(summary = "H-Log 게시글 최신순 가져오기 max 10개")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<List<PhotoArticleResponse>> getPhotoArticleLatest(
            @Schema(description = "게시물 index", example = "0")
            @RequestHeader Integer index,
            @Schema(description = "커뮤니티Id", example = "123")
            @NotNull(message = "커뮤니티Id는 필수 입력값입니다.")
            @RequestHeader Integer communityId,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        List<PhotoArticle> photoArticleList = photoArticleService.getTenArticleLatest(index, communityId, userId);
        return HttpResponse.success(PhotoArticleResponse.of(photoArticleList));
    }

    @GetMapping("/likes")
    @Operation(summary = "H-log 게시글 좋아요 가져오기 max 10개")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<List<PhotoArticleResponse>> getPhotoArticleLikes(
            @Schema(description = "게시물 index", example = "0")
            @RequestHeader Integer index,
            @Schema(description = "커뮤니티Id", example = "123")
            @NotNull(message = "커뮤니티Id는 필수 입력값입니다.")
            @RequestHeader Integer communityId,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        List<PhotoArticle> photoArticleList = photoArticleService.getTenArticleLikes(index, communityId, userId);
        return HttpResponse.success(PhotoArticleResponse.of(photoArticleList));
    }

    @DeleteMapping("")
    @Operation(summary = "H-log 게시글 삭제")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> deleteArticle(
            @RequestBody PhotoArticleRequest.Delete request
    ) {
        photoArticleService.deleteArticle(request.articleId());
        return HttpResponse.successOnly();
    }

    @PostMapping("")
    @Operation(summary = "H-log 게시글 저장")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> createArticle(
            @RequestPart PhotoArticleRequest.Create request,
            @RequestPart("files") List<MultipartFile> files,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        long photoArticleId = photoArticleService.createArticle(request.toArticle(files, userId));
        return HttpResponse.success(DefaultId.of(photoArticleId));
    }

    @GetMapping("/comment")
    @Operation(summary = "H-log 게시글 댓글 가져오기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<List<CommentResponse>> getArticle(
            @Schema(description = "게시글Id", example = "123")
            @NotNull(message = "게시글Id는 필수 입력값입니다.")
            @RequestHeader Long articleId,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        List<Comment> commentResponses = photoArticleService.getArticleComment(articleId, userId);
        return HttpResponse.success(CommentResponse.of(commentResponses));
    }
}
