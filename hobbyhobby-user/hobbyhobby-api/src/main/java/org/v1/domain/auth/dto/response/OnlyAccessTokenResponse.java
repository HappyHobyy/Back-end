package org.v1.domain.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record OnlyAccessTokenResponse(
        @Schema(description = "엑세스 토큰", example = "Bear 121312314124asdfxvq..")
        String accessToken
) {
    public static OnlyAccessTokenResponse from(final String accessToken) {
        return new OnlyAccessTokenResponse(
                accessToken
        );
    }
}
