package org.example.global.config.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtToken(
        String accessSecretKey,
        long accessExpirationSeconds,
        String refreshSecretKey,
        long refreshExpirationSeconds
){}

