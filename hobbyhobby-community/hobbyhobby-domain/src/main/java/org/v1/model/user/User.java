package org.v1.model.user;

public record User(
        Long id,
        String nickname,
        String imageUrl
) {
    public static User onlyUserId(Long id) {
        return new User(id,null,null);
    }
    public static User withId(Long id, String nickname, String imageUrl) {
        return new User(id,nickname,imageUrl);
    }
    public static User withoutUrl(Long id, String nickname) {
        return new User(id,nickname,null);
    }
}
