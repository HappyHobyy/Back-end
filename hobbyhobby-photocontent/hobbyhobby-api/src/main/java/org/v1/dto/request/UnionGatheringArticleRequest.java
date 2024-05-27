package org.v1.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;
import org.v1.global.util.FileUtil;
import org.v1.model.article.GatheringArticle;
import org.v1.model.article.GatheringArticleContent;
import org.v1.model.article.GatheringInfo;
import org.v1.model.imageVideo.ImageVideo;
import org.v1.model.user.User;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public record UnionGatheringArticleRequest(
        Create create,
        Delete delete
) {
    public record Delete(
            @Schema(description = "게시글Id", example = "123")
            @NotNull(message = "게시글Id는 필수 입력값입니다.")
            Long articleId
    ) {
        public GatheringInfo toGatheringInfo() {
            return GatheringInfo.unionGatheringWithArticle(articleId);
        }
    }

    public record Create(
            @Schema(description = "커뮤니티1", example = "{123,456 }")
            @NotNull(message = "필수")
            Integer communityId1,
            @Schema(description = "커뮤니티2", example = "{123,456 }")
            @NotNull(message = "필수")
            Integer communityId2,
            @Schema(description = "모임 제목", example = "title")
            @NotNull(message = "필수")
            String title,
            @Schema(description = "모임 날짜", example = "2024-05-06T15:23:45")
            @NotNull(message = "필수")
            LocalDateTime date,
            @Schema(description = "모임 최대 인원", example = "5")
            @NotNull(message = "필수")
            Integer joinMax,
            @Schema(description = "내용", example = "text")
            @NotNull(message = "필수")
            String text,
            @Schema(description = "장소", example = "location")
            @NotNull(message = "필수")
            String location,
            @Schema(description = "모임 오픈톡 url", example = "http://")
            @NotNull(message = "필수")
            String openTalkLink

    ) {
        public GatheringArticleContent toContent() {
            return new GatheringArticleContent(this.text, this.location, this.date, this.openTalkLink);
        }

        public GatheringArticle toArticle(Long userId,MultipartFile file) {
            try {
                File convertedFile = FileUtil.convertMultipartFileToFile(file);
                ImageVideo image = ImageVideo.withoutPath(0, convertedFile, ImageVideo.FileType.H_LOG);
                return GatheringArticle.initial(User.onlyUserId(userId), this.title, GatheringInfo.unionGatheringWithCommunity(List.of(communityId1, communityId2)),image,joinMax);
            } catch (IOException e) {
                throw new RuntimeException("MultipartFile -> File로 전환이 실패했습니다.", e);
            }
        }
    }
}