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


public record ReviewArticleRequest(
        LatestRequest latest,
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
            String text
    ){
        public ReviewContent toContent(Content.Image image){
            return new ReviewContent(new Content.Text(0,text),image);
        }
        public ReviewArticle toArticle(Long userId) {
            return ReviewArticle.initialize(new Article(title,date, User.onlyUserId(userId),0));
        }
    }
}