package org.v1.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.v1.model.User;

public record UserLoginRequest(
        DefaultLogin defaultLogin,
        OAuthLogin oAuthLogin
) {
    public record DefaultLogin(
            @Schema(description = "이메일", example = "testEmail")
            @NotNull(message = "이메일은 필수 값입니다.")
            String email,

            @Schema(description = "비밀번호", example = "testPassowrd")
            @NotNull(message = "비밀번호 값은 필수 값입니다.")
            String password,
            @Schema(description = "디바이스 토큰", example = "deviceToken")
            @NotNull(message = "디바이스 토큰 값은 필수 값입니다.")
            String deviceToken

    ) {
        public User toUser() {
            return User.withoutId(null, email, User.UserType.OAUTH_DEFAULT, new User.Password(password), null, null, deviceToken,null,null,null,null);
        }
    }

    public record OAuthLogin(
            @Schema(description = "이메일", example = "testEmail")
            @NotNull(message = "이메일은 필수 값입니다.")
            String email,
            @Schema(description = "OAUTH", example = "OAUTH_KAKAO/OAUTH_NAVER/OAUTH_GOOGLE")
            @NotNull(message = "디바이스 토큰 값은 필수 값입니다.")
            User.UserType oAuth,
            @Schema(description = "디바이스 토큰", example = "deviceToken")
            @NotNull(message = "디바이스 토큰 값은 필수 값입니다.")
            String deviceToken
    ) {
        public User toUser() {
            return User.withoutId(null, email,oAuth, null, null, null, deviceToken,null,null,null,null);
        }
    }
}
