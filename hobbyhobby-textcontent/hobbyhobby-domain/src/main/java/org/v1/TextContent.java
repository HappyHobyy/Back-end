package org.v1;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.v1.user.User;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TextContent {
    private final Long id;
    private final String title;
    private final LocalDateTime date;
    private final String category;
    private final String username;
    private final User user;
    private final Integer likes;
    private final Content content;
    private final List<Comment> comment;

    public record Comment(Long id, User user, LocalDateTime date, String text) {
    }
}
