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
            @Schema(description = "이름", example = "testName")
            @NotNull(message = "이름 값은 필수 값입니다.")
            String userName,
            @Schema(description = "전화번호", example = "01011111111")
            @NotNull(message = "전화번호 값은 필수 값입니다.")
            String phoneNumber,
            @Schema(description = "성별", example = "MAN/WOMAN")
            @NotNull(message = "성별 값은 필수 값입니다.")
            User.UserGender gender,
            @Schema(description = "내국인/외국인", example = "DOMESTIC/FOREIGNER")
            @NotNull(message = "내국인/외국인 값은 필수 값입니다.")
            User.Nationality nationality,
            @Schema(description = "OAUTH", example = "OAUTH_KAKAO/OAUTH_NAVER/OAUTH_GOOGLE")
            @NotNull(message = "유저타입 값은 필수 값입니다.")
            User.UserType oAuth,
            @Schema(description = "생일", example = "2001/12/12")
            @NotNull(message = "생일 값은 필수 값입니다.")
            String birth
    ) {
        public User toUser() {
            return User.withoutId(nickname, email, oAuth, new User.Password(null), gender, nationality, null,null,userName,phoneNumber,birth);
        }
    }

    public record DefaultRegister(
            @Schema(description = "이메일", example = "testEmail")
            @NotNull(message = "이메일은 필수 값입니다.")
            String email,
            @Schema(description = "닉네임", example = "testNickName")
            @NotNull(message = "닉네임 값은 필수 값입니다.")
            String nickname,
            @Schema(description = "이름", example = "testName")
            @NotNull(message = "이름 값은 필수 값입니다.")
            String userName,
            @Schema(description = "전화번호", example = "01011111111")
            @NotNull(message = "전화번호 값은 필수 값입니다.")
            String phoneNumber,
            @Schema(description = "비밀번호", example = "testPassword")
            @NotNull(message = "비밀번호 값은 필수 값입니다.")
            String password,
            @Schema(description = "성별", example = "MAN/WOMAN")
            @NotNull(message = "성별 값은 필수 값입니다.")
            User.UserGender gender,
            @Schema(description = "내국인/외국인", example = "DOMESTIC/FOREIGNER")
            @NotNull(message = "내국인/외국인 값은 필수 값입니다.")
            User.Nationality nationality,
            @Schema(description = "생일", example = "2001/12/12")
            @NotNull(message = "생일 값은 필수 값입니다.")
            String birth
    ) {
        public User toUser() {
            return User.withoutId(nickname, email, User.UserType.OAUTH_DEFAULT, new User.Password(password), gender, nationality, null,null,userName,phoneNumber,birth);
        }
    }
}
