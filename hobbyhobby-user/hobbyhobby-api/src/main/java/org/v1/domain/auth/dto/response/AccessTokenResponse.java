package org.v1.domain.auth.dto.response;

public record AccessTokenResponse(
        String accessToken
) {
    public static AccessTokenResponse from(final String token) {
        return new AccessTokenResponse(
                token
        );
    }
}
