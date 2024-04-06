package org.v1.util;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtToken(
        String accessSecretKey,
        long accessExpirationSeconds,
        String refreshSecretKey,
        long refreshExpirationSeconds
){}

