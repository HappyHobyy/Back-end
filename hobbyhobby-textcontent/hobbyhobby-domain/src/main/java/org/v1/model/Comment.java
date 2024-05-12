package org.v1.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
public class Comment {
    private final User user;
    private final LocalDateTime date;
    private final String text;
    private final UserStatus userStatus;
    public Comment validateUserComment(Long commandUserId) {
        return new Comment(user, date, text,UserStatus.onlyIsUserArticleOwner(commandUserId.equals(getUser().id())));
    }
}