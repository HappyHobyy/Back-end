package org.v1.dto;

import org.v1.model.ArticleDetail;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public record ArticleDetailResponse(
        List<Text> text,
        List<Image> images,
        List<Comment> comments,
        boolean isUserArticleOwner,
        boolean isUserLiked
) {
    public record Text(Integer index, String text) {
        public static Text of(ArticleDetail.Text text) {
            return new Text(text.index(), text.text());
        }
    }

    public record Image(Integer index, String path) {
        public static Image of(ArticleDetail.Image image) {
            return new Image(image.index(), image.path());
        }
    }

    public record Comment(Long commentId, Long userId, String userNickname, LocalDateTime date, String text,
                          boolean isUserCommentOwner) {
        public static Comment of(ArticleDetail.Comment comment) {
            return new Comment(comment.id(), comment.user().id(), comment.user().nickname(), comment.date(), comment.text(), comment.user().id().equals(comment.user().id()));
        }
    }

    public static List<ArticleDetailResponse> of(ArticleDetail articleDetail) {
        return Optional.ofNullable(articleDetail)
                .map(detail -> new ArticleDetailResponse(
                        Optional.ofNullable(detail.getTexts())
                                .map(texts -> texts.stream().map(Text::of).toList())
                                .orElse(Collections.emptyList()),
                        Optional.ofNullable(detail.getImages())
                                .map(images -> images.stream().map(Image::of).toList())
                                .orElse(Collections.emptyList()),
                        Optional.ofNullable(detail.getComments())
                                .map(comments -> comments.stream().map(Comment::of).toList())
                                .orElse(Collections.emptyList()),
                        Optional.of(detail.isUserArticleOwner()).orElse(false),
                        Optional.of(detail.isUserLiked()).orElse(false)
                ))
                .map(List::of)
                .orElse(Collections.emptyList());
    }
}
