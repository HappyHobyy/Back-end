package org.v1.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticleDetail {
    private final List<Comment> comments;
    private final Content content;
    private final UserStatus userStatus;

    public record UserStatus(boolean isUserLiked, boolean isUserArticleOwner) {
    }

    public ArticleDetail changeUserStatus(UserStatus userStatus) {
        return new ArticleDetail(this.comments, this.content,userStatus);
    }
}
