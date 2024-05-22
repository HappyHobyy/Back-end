package org.v1.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.v1.model.User;

public record UserGetMyPageResponse(
        @Schema(description = "이메일", example = "testEmail")
        String email,
        @Schema(description = "닉네임", example = "testNickName")
        String nickname,
        @Schema(description = "성별", example = "MAN/WOMAN")
        User.UserGender gender,
        @Schema(description = "내국인/외국인", example = "DOMESTIC/FOREIGNER")
        User.Nationality nationality,
        @Schema(description = "이미지 URL", example = "https://asdfsadf.com/123")
        String imageUrl,
        @Schema(description = "이름", example = "testName")
        String userName,
        @Schema(description = "전화번호", example = "01011111111")
        String phoneNumber

) {
    public static UserGetMyPageResponse of(final User user) {
        return new UserGetMyPageResponse(
                user.getEmail(),
                user.getNickname(),
                user.getUserGender(),
                user.getNationality(),
                user.getImageUrl(),
                user.getUserName(),
                user.getPhoneNumber()
        );
    }
}
