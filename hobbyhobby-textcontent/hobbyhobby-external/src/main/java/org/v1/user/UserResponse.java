package org.v1.user;

import org.v1.model.Community;
import org.v1.model.User;

import java.util.List;
import java.util.stream.Collectors;

public record UserResponse(
   Long userId,
   String userName,
   String path
) {
    public User toUser() {
        return new User(this.userId, this.userName, this.path);
    }
}