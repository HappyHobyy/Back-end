package org.v1.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticleDetail {
    private final List<Text> texts;
    private final List<Image> images;
    private final List<Comment> comments;
    private final boolean isUserLiked;
    private final boolean isUserArticleOwner;
    public record Image(Integer index, String path) {
    }
    public record Text(Integer index, String text) {
    }
    public record Comment(Long id, User user, LocalDateTime date, String text,boolean isUserCommentOwner) {
    }
}
