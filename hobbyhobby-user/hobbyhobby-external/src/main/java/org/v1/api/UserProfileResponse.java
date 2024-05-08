package org.v1.api;

import org.v1.model.User;

public record UserProfileResponse(Long userId, String userName, String imageUrl) {
    public static UserProfileResponse ofUser(User user) {
        return new UserProfileResponse(user.getId().value(), user.getNickname(), user.getImageUrl());
    }
}
