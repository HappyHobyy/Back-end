package org.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;
import org.v1.model.photoartlcle.PhotoArticle;
import org.v1.model.photoartlcle.PhotoAriticleContent;
import org.v1.model.user.User;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.v1.global.util.FileUtil.convertMultipartFileToFile;

public record PhotoArticleRequest(
        GetRequest latest,
        CreateRequest create,
        DeleteRequest delete
) {
    public record GetRequest(
            @Schema(description = "커뮤니티Id", example = "123")
            @NotNull(message = "커뮤니티Id는 필수 입력값입니다.")
            Integer communityId
    ) {}
    public record DeleteRequest(
            @Schema(description = "게시글Id", example = "123")
            @NotNull(message = "게시글Id는 필수 입력값입니다.")
            Long photoArticleId
    ){}
    public record CreateRequest(
            @Schema(description = "커뮤니티Id", example = "123")
            @NotNull(message = "필수")
            Integer communityId,
            @Schema(description = "게시글 날짜", example = "2024-05-06T15:23:45.123456789")
            @NotNull(message = "필수")
            LocalDateTime date,
            @Schema(description = "내용", example = "text")
            @NotNull(message = "필수")
            String text
    ){
        public PhotoAriticleContent toContent(List<MultipartFile> multipartFiles) {
            List<PhotoAriticleContent.ImageVideo> fileList = multipartFiles != null ?
                    multipartFiles.stream()
                            .map(file -> {
                                try {
                                    File convertedFile = convertMultipartFileToFile(file);
                                    return PhotoAriticleContent.ImageVideo.withoutPath(multipartFiles.indexOf(file), convertedFile, PhotoAriticleContent.FileType.H_LOG);
                                } catch (IOException e) {
                                    throw new RuntimeException("MultipartFile -> File로 전환이 실패했습니다.",e);
                                }
                            })
                            .toList() :
                    Collections.emptyList();
            return new PhotoAriticleContent(this.text, fileList);
        }

        public PhotoArticle toArticle(Long userId) {
            return PhotoArticle.withoutId(this.date(),User.onlyUserId(userId));
        }
    }
}
