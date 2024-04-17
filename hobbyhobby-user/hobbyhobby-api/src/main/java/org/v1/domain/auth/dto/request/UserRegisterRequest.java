package org.v1.domain.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.v1.domain.user.domain.User;

public record UserRegisterRequest(
        @Schema(description = "닉네임", example = "testNickname")
        @NotNull(message = "닉네임은 필수 값입니다.")
        String nickname,

        @Schema(description = "이메일", example = "testEmail")
        @NotNull(message = "이메일은 필수 값입니다.")
        String email,

        @Schema(description = "비밀번호", example = "testPassowrd")
        @NotNull(message = "비밀번호 값은 필수 값입니다.")
        String password

) {
    public User toEntity(){
        return User.builder().
                nickname(nickname).email(email).password(password).type("DEFAULT").role("ROLE_USER").build();
    }
}
