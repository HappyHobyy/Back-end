package org.v1.domain.auth.dto.response;

public record OnlyAccessTokenResponse(
        String accessToken
) {
    public static OnlyAccessTokenResponse from(final String accessToken) {
        return new OnlyAccessTokenResponse(
                accessToken
        );
    }
}
