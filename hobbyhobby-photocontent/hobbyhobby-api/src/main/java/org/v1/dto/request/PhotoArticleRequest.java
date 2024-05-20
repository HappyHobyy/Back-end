package org.v1.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;
import org.v1.model.imageVideo.ImageVideo;
import org.v1.model.article.PhotoArticle;
import org.v1.model.article.PhotoAriticleContent;
import org.v1.model.user.User;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.v1.global.util.FileUtil.convertMultipartFileToFile;

public record PhotoArticleRequest(
        Search latest,
        Create create,
        Delete delete
) {
    public record Search(
            @Schema(description = "커뮤니티Id", example = "123")
            @NotNull(message = "커뮤니티Id는 필수 입력값입니다.")
            Integer communityId
    ) {

    }
    public record Delete(
            @Schema(description = "게시글Id", example = "123")
            @NotNull(message = "게시글Id는 필수 입력값입니다.")
            Long articleId
    ){
    }
    public record Create(
            @Schema(description = "커뮤니티Id", example = "123")
            @NotNull(message = "필수")
            Integer communityId,
            @Schema(description = "내용", example = "text")
            @NotNull(message = "필수")
            String text
    ){
        public PhotoArticle toArticle(List<MultipartFile> multipartFiles,Long userId) {
            List<ImageVideo> fileList = multipartFiles != null ?
                    multipartFiles.stream()
                            .map(file -> {
                                try {
                                    File convertedFile = convertMultipartFileToFile(file);
                                    return ImageVideo.withoutPath(multipartFiles.indexOf(file), convertedFile, ImageVideo.FileType.H_LOG);
                                } catch (IOException e) {
                                    throw new RuntimeException("MultipartFile -> File로 전환이 실패했습니다.",e);
                                }
                            })
                            .toList() :
                    Collections.emptyList();
            return PhotoArticle.initial(User.onlyUserId(userId),communityId,new PhotoAriticleContent(this.text, fileList));
        }
    }
}
