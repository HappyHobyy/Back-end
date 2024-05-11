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
    private String text;
    private boolean isUserCommentOwner;
    public Comment validateUserComment(Long commandUserId) {
        return new Comment(user, date, text, commandUserId.equals(user.id()));
    }
}