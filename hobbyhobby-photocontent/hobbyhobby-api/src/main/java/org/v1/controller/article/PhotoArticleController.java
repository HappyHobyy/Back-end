package org.v1.controller.article;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
    @Operation(summary = "H-Log 게시글 최신순 제목 가져오기 max 10개")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<List<PhotoArticleResponse>> getPhotoArticleLatest(
            @RequestBody PhotoArticleRequest.Get request,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        List<PhotoArticle> photoArticleList = photoArticleService.getTenArticleLatest(request.communityId(),userId);
        return HttpResponse.success(PhotoArticleResponse.of(photoArticleList));
    }

    @GetMapping("/likes")
    @Operation(summary = "H-log 게시글 좋아요 제목 가져오기 max 10개")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<List<PhotoArticleResponse>> getPhotoArticleLikes(
            @RequestBody PhotoArticleRequest.Get request,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        List<PhotoArticle> photoArticleList = photoArticleService.getTenArticleLikes(request.communityId(),userId);
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

    @GetMapping("/detail")
    @Operation(summary = "H-log 게시글 댓글 가져오기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<List<CommentResponse>> getArticle(
            @RequestBody Long photoArticleId,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        List<Comment> commentResponses = photoArticleService.getArticleComment(photoArticleId, userId);
        return HttpResponse.success(CommentResponse.of(commentResponses));
    }
}
