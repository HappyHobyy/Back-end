package org.v1.domain.auth.dto.response;

public record RefreshTokenResponse(
        String refreshToken
) {
    public static RefreshTokenResponse from(final String token) {
        return new RefreshTokenResponse(
                token
        );
    }
}
