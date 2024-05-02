package org.v1.domain.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.v1.domain.user.domain.User;

public record NicknameCheckRequest(
        @Schema(description = "닉네임", example = "testNickname")
        @NotNull(message = "닉네임은 필수 입력값입니다.")
        String nickname
) {
    public User toUser() {
        return User.withoutId(nickname, null, null, null, User.UserRole.ROLE_USER, null, null, null);
    }
}