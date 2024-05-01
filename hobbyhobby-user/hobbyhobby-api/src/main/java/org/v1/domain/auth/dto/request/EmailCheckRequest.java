package org.v1.domain.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.v1.domain.user.domain.User;

public record EmailCheckRequest(
        @Schema(description = "이메일", example = "testEmail")
        @NotNull(message = "이메일은 필수 입력값입니다.")
        String email
) {
    public User toUser() {
        return User.withoutId(null, email, null, null, User.UserRole.ROLE_USER, null, null, null);
    }
}