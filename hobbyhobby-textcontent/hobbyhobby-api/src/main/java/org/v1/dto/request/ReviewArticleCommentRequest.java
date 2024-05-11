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

import static org.v1.global.util.FileUtil.convertMultipartFileToFile;

public record ReviewArticleCommentRequest(
        Create create,
        Delete delete
) {
    public record Create(
            @Schema(description = "게시글Id", example = "123")
            @NotNull(message = "게시글Id는 필수 입력값입니다.")
            Long articleId,
            @Schema(description = "별점", example = "4.512321")
            @NotNull(message = "리뷰 별점는 필수 입력값입니다.")
            double star,
            List<TextRequest> textList
    ){
        public record TextRequest(
                @Schema(description = "순서", example = "1")
                @NotNull(message = "필수")
                Integer index,
                @Schema(description = "내용", example = "text")
                @NotNull(message = "필수")
                String text
        ){}
        private Content toContent(List<Content.Image> imageList) {
            List<Content.Text> textList = this.textList() != null ?
                    this.textList().stream()
                            .map(textRequest -> new Content.Text(textRequest.index(), textRequest.text()))
                            .toList() :
                    Collections.emptyList();
            return new Content(textList, imageList);
        }
        private Comment toComment(Long userId) {
            return new Comment(User.onlyUserId(userId),LocalDateTime.now(),null);
        }
        public ReviewComment toReviewComment(List<Content.Image> imageList, Long userId) {
            return ReviewComment.withoutId(toComment(userId),star,toContent(imageList));
        }
    }
    public record Delete(
            @Schema(description = "댓글Id", example = "456")
            @NotNull(message = "댓글Id는 필수 입력값입니다.")
            Long commentId
    ) {}
}
