package org.v1.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.v1.domain.user.domain.User;

public record UserOAuthLoginRequest(
        @Schema(description = "이메일", example = "testEmail")
        @NotNull(message = "이메일은 필수 값입니다.")
        String email,

        @Schema(description = "닉네임", example = "testNickName")
        @NotNull(message = "닉네임 값은 필수 값입니다.")
        String nickname
) {

        public User toKakaoEntity(){
                return User.builder().email(email).nickname(nickname).type("Kakao").role("ROLE_USER").build();
        }
        public User toGoogleEntity(){
                return User.builder().email(email).nickname(nickname).type("Google").role("ROLE_USER").build();
        }

}
