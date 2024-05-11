package org.v1.model;

public record UserStatus(boolean isUserLiked, boolean isUserArticleOwner) {
    public static UserStatus onlyIsUserArticleOwner(boolean isUserArticleOwner) {
        return new UserStatus(false,isUserArticleOwner);
    }
}