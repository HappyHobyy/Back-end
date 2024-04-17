package org.v1.domain.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.v1.domain.user.domain.User;

public record UserResetPasswordRequest(
        @Schema(description = "이메일", example = "testEmail")
        @NotNull(message = "이메일은 필수 값입니다.")
        String email
) {
}
