package org.v1.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record TokenResponse(
        @Schema(description = "리프레시 토큰", example = "Bear 123sasdfvas..")
        String refreshToken,
        @Schema(description = "엑세스 토큰", example = "Bear 121312314124asdfxvq..")
        String accessToken
){
    public static TokenResponse from(final String refreshToken, final String accessToken) {
        return new TokenResponse(
                refreshToken,
                accessToken
        );
    }
}