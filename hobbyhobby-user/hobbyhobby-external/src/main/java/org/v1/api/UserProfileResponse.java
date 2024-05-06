package org.v1.api;

import org.v1.domain.user.domain.User;

public record UserProfileResponse(Long userId, String userName, String imageUrl) {
    public static UserProfileResponse ofUser(User user) {
        return new UserProfileResponse(userId, userName, imageUrl);
    }
}
