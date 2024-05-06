package org.v1.model;

public record User(
        Long id,
        String nickname,
        String path
) {
    public static User onlyUserId(Long id) {
        return new User(id,null,null);
    }
}
