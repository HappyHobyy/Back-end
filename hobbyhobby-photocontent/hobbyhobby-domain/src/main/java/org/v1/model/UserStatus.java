package org.v1.model;

public record UserStatus(boolean isUserLiked, boolean isUserOwner) {
    public static UserStatus onlyIsUserArticleOwner(boolean isUserOwner) {
        return new UserStatus(false,isUserOwner);
    }
}