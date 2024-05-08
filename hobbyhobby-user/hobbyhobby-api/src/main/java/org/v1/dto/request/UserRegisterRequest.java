package org.v1.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.v1.model.User;

public record UserRegisterRequest(
        OAuthRegister oAuthRegister
) {
    public record OAuthRegister(
            @Schema(description = "이메일", example = "testEmail")
            @NotNull(message = "이메일은 필수 값입니다.")
            String email,
            @Schema(description = "닉네임", example = "testNickName")
            @NotNull(message = "닉네임 값은 필수 값입니다.")
            String nickname,
            @Schema(description = "성별", example = "MAN/WOMAN")
            @NotNull(message = "성별 값은 필수 값입니다.")
            User.UserGender gender,
            @Schema(description = "내국인/외국인", example = "DOMESTIC/FOREIGNER")
            @NotNull(message = "내국인/외국인 값은 필수 값입니다.")
            User.Nationality nationality,
            @Schema(description = "OAUTH", example = "OAUTH_KAKAO/OAUTH_NAVER/OAUTH_GOOGLE")
            @NotNull(message = "디바이스 토큰 값은 필수 값입니다.")
            User.UserType oAuth
    ) {
        public User toUser() {
            return User.withoutId(nickname, email, oAuth, new User.Password(null), User.UserRole.ROLE_USER, gender, nationality, null,null);
        }
    }

    public record DefaultRegister(
            @Schema(description = "이메일", example = "testEmail")
            @NotNull(message = "이메일은 필수 값입니다.")
            String email,
            @Schema(description = "닉네임", example = "testNickName")
            @NotNull(message = "닉네임 값은 필수 값입니다.")
            String nickname,
            @Schema(description = "비밀번호", example = "testPassword")
            @NotNull(message = "비밀번호 값은 필수 값입니다.")
            String password,
            @Schema(description = "성별", example = "MAN/WOMAN")
            @NotNull(message = "성별 값은 필수 값입니다.")
            User.UserGender gender,
            @Schema(description = "내국인/외국인", example = "DOMESTIC/FOREIGNER")
            @NotNull(message = "내국인/외국인 값은 필수 값입니다.")
            User.Nationality nationality
    ) {
        public User toUser() {
            return User.withoutId(nickname, email, User.UserType.OAUTH_DEFAULT, new User.Password(password), User.UserRole.ROLE_USER, gender, nationality, null,null);
        }
    }
}
