package org.v1.model.comment;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.v1.model.user.User;
import org.v1.model.user.UserStatus;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Comment {
    private Long id;
    private User user;
    private LocalDateTime date;
    private String text;
    private UserStatus userStatus;
    public static Comment initial(User user, LocalDateTime date, String text) {
        return new Comment(null, user, date, text, null);
    }
    public Comment changeUser(Long commandUserId) {
        return new Comment(id, user, date, text, new UserStatus(commandUserId.equals(user.id()),userStatus.isUserOwner(),userStatus.isUserJoined()));
    }
}