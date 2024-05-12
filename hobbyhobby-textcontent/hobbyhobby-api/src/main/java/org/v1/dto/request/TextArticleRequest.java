package org.v1.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;
import org.v1.model.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record TextArticleRequest(
        LatestRequest latest,
        SearchRequest search,
        CreateRequest create,
        DeleteRequest delete
) {
    public record LatestRequest(
            @Schema(description = "커뮤니티Id", example = "123")
            @NotNull(message = "커뮤니티Id는 필수 입력값입니다.")
            Long communityId
    ) {}
    public record DeleteRequest(
            @Schema(description = "게시글Id", example = "123")
            @NotNull(message = "게시글Id는 필수 입력값입니다.")
            Long articleId
    ){}

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
            List<TextRequest> textList
    ){
        public record TextRequest(
                @Schema(description = "순서", example = "1")
                @NotNull(message = "필수")
                Integer index,
                @Schema(description = "내용", example = "text")
                @NotNull(message = "필수")
                String text
        ){};

        public Content toContent(List<Content.Image> images) {
            List<Content.Text> textList = this.textList() != null ?
                    this.textList().stream()
                            .map(textRequest -> new Content.Text(textRequest.index(), textRequest.text()))
                            .toList() :
                    Collections.emptyList();
            return new Content(textList, images);
        }

        public TextArticle toArticle(Long userId) {
            return TextArticle.withoutId(new Article(title,date, User.onlyUserId(userId),0), 0);
        }
    }
}
