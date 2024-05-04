package org.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.v1.model.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public record ArticleRequest(
        LatestRequest latest,
        SearchRequest search,
        CreateRequest create
) {
    public record LatestRequest(
            @Schema(description = "커뮤니티Id", example = "123")
            @NotNull(message = "커뮤니티Id는 필수 입력값입니다.")
            Long communityId
    ) {}
    public record SearchRequest(
            @Schema(description = "커뮤니티Id", example = "123")
            @NotNull(message = "커뮤니티Id는 필수 입력값입니다.")
            Long communityId,
            @Schema(description = "검색명", example = "123")
            @NotNull(message = "검색명은 필수 입력값입니다.")
            String search
    ) {
        public Search toSearch() {
            return new Search(communityId, search);
        }
    }
    public record CreateRequest(
            @Schema(description = "커뮤니티Id", example = "123")
            @NotNull(message = "필수")
            Long communityId,
            @Schema(description = "게시글 제목", example = "testName")
            @NotNull(message = "필수")
            String title,
            @Schema(description = "게시글 날짜", example = "2024-05-06T15:23:45.123456789")
            @NotNull(message = "필수")
            LocalDateTime date,
            List<TextRequest> textList,
            List<ImageRequest> imageList
    ){
        public record TextRequest(
                @Schema(description = "순서", example = "1")
                @NotNull(message = "필수")
                Integer index,
                @Schema(description = "내용", example = "text")
                @NotNull(message = "필수")
                String text
        ){};
        public record ImageRequest(
                @Schema(description = "순서", example = "1")
                @NotNull(message = "필수")
                Integer index,
                @Schema(description = "바이트 인코딩한 데이터", example = "1")
                @NotNull(message = "필수")
                byte[] imageData
        ) {}
        public Content toContent() {
            List<Content.Text> textList = this.textList() != null ?
                    this.textList().stream()
                            .map(textRequest -> new Content.Text(textRequest.index(), textRequest.text()))
                            .toList() :
                    Collections.emptyList();

            List<Content.Image> imageList = this.imageList() != null ?
                    this.imageList().stream()
                            .map(imageRequest -> Content.Image.withoutPath(imageRequest.index(), imageRequest.imageData()))
                            .toList() :
                    Collections.emptyList();

            return new Content(textList, imageList);
        }
        public Article toArticle(Long userId, String nickname) {
            return Article.withoutId(this.title(), this.date(), new User(userId, nickname), 0);
        }
    }
}
