package org.example.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.example.domain.user.domain.User;

public record UserLoginRequest(
        @Schema(description = "이메일", example = "testEmail")
        @NotNull(message = "이메일은 필수 값입니다.")
        String email,

        @Schema(description = "비밀번호", example = "testPassowrd")
        @NotNull(message = "비밀번호 값은 필수 값입니다.")
        String password
) {
    public User toEntity(){
        return User.builder().email(email).password(password).build();
    }
}
