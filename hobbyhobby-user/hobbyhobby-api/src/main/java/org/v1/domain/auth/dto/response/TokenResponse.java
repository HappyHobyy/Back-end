package org.v1.domain.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record TokenResponse(
        String refreshToken,
        String accessToken
){
    public static TokenResponse from(final String refreshToken, final String accessToken) {
        return new TokenResponse(
                refreshToken,
                accessToken
        );
    }
}