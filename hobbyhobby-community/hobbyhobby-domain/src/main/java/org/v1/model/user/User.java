package org.v1.model.user;

public record User(
        Long id,
        String nickname,
        String path
) {
    public static User onlyUserId(Long id) {
        return new User(id,null,null);
    }
}
