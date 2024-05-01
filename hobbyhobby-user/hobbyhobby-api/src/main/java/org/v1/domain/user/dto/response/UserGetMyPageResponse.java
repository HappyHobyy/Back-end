package org.v1.domain.user.dto.response;

import lombok.Builder;
import org.v1.domain.user.domain.User;

import java.util.List;

public record UserGetMyPageResponse(
        String nickname,
        String email
) {
    public static UserGetMyPageResponse of(final User user) {
        return new UserGetMyPageResponse(
                user.getNickname(),
                user.getEmail()
        );
    }
}
