package org.v1.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
public class Comment {
    private User user;
    private LocalDateTime date;
    private UserStatus userStatus;
    public Comment validateUserComment(Long commandUserId) {
        return new Comment(user, date, UserStatus.onlyIsUserArticleOwner(commandUserId.equals(getUser().id())));
    }
}