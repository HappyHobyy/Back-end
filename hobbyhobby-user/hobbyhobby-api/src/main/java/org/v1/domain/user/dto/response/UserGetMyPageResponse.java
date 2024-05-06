package org.v1.domain.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.v1.domain.user.domain.User;

import java.util.List;

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
        String imageUrl
) {
    public static UserGetMyPageResponse of(final User user) {
        return new UserGetMyPageResponse(
                user.getEmail(),
                user.getNickname(),
                user.getUserGender(),
                user.getNationality(),
                user.getImageUrl()
        );
    }
}
