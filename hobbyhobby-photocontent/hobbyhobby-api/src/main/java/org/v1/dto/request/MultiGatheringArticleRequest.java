package org.v1.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;
import org.v1.model.article.GatheringArticle;
import org.v1.model.article.GatheringArticleContent;
import org.v1.model.article.GatheringInfo;
import org.v1.model.imageVideo.ImageVideo;
import org.v1.model.user.User;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.v1.global.util.FileUtil.convertMultipartFileToFile;

public record MultiGatheringArticleRequest(

        Detail detail,
        Search latest,
        Create create,
        Delete delete
) {
    public record Detail(
            @Schema(description = "게시물ID", example = "123")
            @NotNull(message = "게시물ID는 필수 입력값입니다.")
            Long articleId
    ){
        public GatheringInfo toGatheringInfo() {
            return GatheringInfo.multiGatheringWithArticle(articleId);
        }
    }
    public record Search(
            @Schema(description = "커뮤니티Ids", example = "{123,456}")
            @NotNull(message = "필수")
            List<Integer> communityIds
    ) {
        public GatheringInfo toGatheringInfo() {
            return GatheringInfo.multiGatheringWithCommunity(communityIds);
        }
    }
    public record Delete(
            @Schema(description = "게시글Id", example = "123")
            @NotNull(message = "게시글Id는 필수 입력값입니다.")
            Long articleId
    ) {
        public GatheringInfo toGatheringInfo() {
            return GatheringInfo.multiGatheringWithArticle(articleId);
        }
    }

    public record Create(
            @Schema(description = "커뮤니티Ids", example = "{123,456 }")
            @NotNull(message = "필수")
            List<Integer> communityIds,
            @Schema(description = "모임 날짜", example = "2024-05-06T15:23:45.123456789")
            @NotNull(message = "필수")
            LocalDateTime date,
            @Schema(description = "내용", example = "text")
            @NotNull(message = "필수")
            String text,
            @Schema(description = "장소", example = "location")
            @NotNull(message = "필수")
            String location
    ) {
        public GatheringArticleContent toContent(MultipartFile file) {
            try {
                File convertedFile = convertMultipartFileToFile(file);
                ImageVideo image = ImageVideo.withoutPath(0, convertedFile, ImageVideo.FileType.H_LOG);
                return new GatheringArticleContent(this.text, this.location, this.date, image);
            } catch (IOException e) {
                throw new RuntimeException("MultipartFile -> File로 전환이 실패했습니다.", e);
            }
        }
        public GatheringArticle toArticle(Long userId) {
            return GatheringArticle.initial(User.onlyUserId(userId),GatheringInfo.multiGatheringWithCommunity(communityIds));
        }
    }
}