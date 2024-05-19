package org.v1.controller.photoarticle;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.v1.dto.PhotoArticleDetailResponse;
import org.v1.dto.PhotoArticleRequest;
import org.v1.dto.PhotoArticleResponse;
import org.v1.model.photoartlcle.PhotoArticle;
import org.v1.model.photoartlcle.PhotoArticleDetail;
import org.v1.service.photoarticle.PhotoArticleService;
import response.DefaultId;
import response.HttpResponse;

import java.util.List;

@Tag(name = "H-Log", description = "H-Log API")
@RestController
@AllArgsConstructor
@RequestMapping("/api/article")
public class PhotoArticleController {
    private final PhotoArticleService photoArticleService;
    @GetMapping("/latest")
    @Operation(summary = "H-Log 게시글 최신순 제목 가져오기 max 10개")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> getPhotoArticleLatest(
            @RequestBody PhotoArticleRequest.GetRequest articleRequest
    ) {
        List<PhotoArticle> photoArticleList = photoArticleService.getTenArticleLatest(articleRequest.communityId());
        return HttpResponse.success(PhotoArticleResponse.of(photoArticleList));
    }
    @GetMapping("/likes")
    @Operation(summary = "H-log 게시글 최신순 제목 가져오기 max 10개")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> getPhotoArticleLikes(
            @RequestBody PhotoArticleRequest.GetRequest articleRequest
    ) {
        List<PhotoArticle> photoArticleList = photoArticleService.getTenArticleLikes(articleRequest.communityId());
        return HttpResponse.success(PhotoArticleResponse.of(photoArticleList));
    }
    @DeleteMapping("")
    @Operation(summary = "H-log 게시글 삭제")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> deleteArticle(
            @RequestBody PhotoArticleRequest.DeleteRequest articleRequest
    ) {
        photoArticleService.deleteArticle(articleRequest.photoArticleId());
        return HttpResponse.successOnly();
    }
    @PostMapping("")
    @Operation(summary = "H-log 게시글 저장")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> createArticle(
            @RequestPart PhotoArticleRequest.CreateRequest articleRequest,
            @RequestPart("files") List<MultipartFile> files,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        Long photoArticleId = photoArticleService.createArticle(articleRequest.toArticle(userId),articleRequest.toContent(files));
        return HttpResponse.success(DefaultId.of(photoArticleId));
    }
    @GetMapping("/detail")
    @Operation(summary = "H-log 게시글 내용 가져오기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<List<PhotoArticleDetailResponse>> getArticle(
            @RequestBody Long photoArticleId,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        PhotoArticleDetail photoArticleDetail = photoArticleService.getArticleDetail(photoArticleId,userId);
        return HttpResponse.success(PhotoArticleDetailResponse.of(photoArticleDetail));
    }
}
