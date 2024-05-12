package org.v1.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Comment {
    private Long id;
    private User user;
    private LocalDateTime date;
    private String text;
    private UserStatus userStatus;
    public static Comment withoutId(User user, LocalDateTime date, String text, UserStatus userStatus) {
        return new Comment(null, user, date, text, userStatus);
    }
    public Comment changeUser(Long commandUserId) {
        return new Comment(id, user, date, text, UserStatus.onlyIsUserArticleOwner(commandUserId.equals(user.id())));
    }
}