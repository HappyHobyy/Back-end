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
    private boolean isUserCommentOwner;
    public static Comment withoutId(User user, LocalDateTime date, String text, boolean isUserCommentOwner) {
        return new Comment(null, user, date, text, isUserCommentOwner);
    }
    public Comment changeUser(User user,Long commandUserId) {
        return new Comment(id, user, date, text, commandUserId.equals(user.id()));
    }
}