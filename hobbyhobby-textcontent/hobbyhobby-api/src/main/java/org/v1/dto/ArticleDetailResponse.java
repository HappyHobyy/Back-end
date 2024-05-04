package org.v1.dto;

import org.v1.model.ArticleDetail;
import org.v1.model.Comment;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public record ArticleDetailResponse(
        List<TextResponse> text,
        List<ImageResponse> images,
        List<CommentResponse> comments,
        boolean isUserArticleOwner,
        boolean isUserLiked
) {
    public record TextResponse(Integer index, String text) {
        public static TextResponse of(ArticleDetail.Text text) {
            return new TextResponse(text.index(), text.text());
        }
    }

    public record ImageResponse(Integer index, String path) {
        public static ImageResponse of(ArticleDetail.Image image) {
            return new ImageResponse(image.index(), image.path());
        }
    }

    public record CommentResponse(Long commentId, Long userId, String userNickname, LocalDateTime date, String text,
                          boolean isUserCommentOwner) {
        public static CommentResponse of(Comment comment) {
            return new CommentResponse(comment.id(), comment.user().id(), comment.user().nickname(), comment.date(), comment.text(), comment.user().id().equals(comment.user().id()));
        }
    }

    public static List<ArticleDetailResponse> of(ArticleDetail articleDetail) {
        return Optional.ofNullable(articleDetail)
                .map(detail -> new ArticleDetailResponse(
                        Optional.ofNullable(detail.getTexts())
                                .map(texts -> texts.stream().map(TextResponse::of).toList())
                                .orElse(Collections.emptyList()),
                        Optional.ofNullable(detail.getImages())
                                .map(images -> images.stream().map(ImageResponse::of).toList())
                                .orElse(Collections.emptyList()),
                        Optional.ofNullable(detail.getComments())
                                .map(comments -> comments.stream().map(CommentResponse::of).toList())
                                .orElse(Collections.emptyList()),
                        Optional.of(detail.isUserArticleOwner()).orElse(false),
                        Optional.of(detail.isUserLiked()).orElse(false)
                ))
                .map(List::of)
                .orElse(Collections.emptyList());
    }
}
