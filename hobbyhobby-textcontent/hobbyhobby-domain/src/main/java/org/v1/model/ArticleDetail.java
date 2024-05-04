package org.v1.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticleDetail {
    private final List<Text> texts;
    private final List<Image> images;
    private final List<Comment> comments;
    private final UserStatus userStatus;
    public record Image(Integer index, String path) {
    }
    public record Text(Integer index, String text) {
    }
    public record UserStatus(boolean isUserLiked,boolean isUserArticleOwner){}
    public ArticleDetail changeUserStatus(UserStatus userStatus) {
        return new ArticleDetail(this.texts,this.images,this.comments,userStatus);
    }
}
