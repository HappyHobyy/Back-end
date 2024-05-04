package org.v1.global.config.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {
    public static final int TOKEN_BEGIN_INDEX = 7;

    private final JwtToken jwtToken;

    public String subStringToken(final String token){
        return token.substring(TOKEN_BEGIN_INDEX);
    }
    public String extractAccessUserId(final String token) {
        return extractClaim(subStringToken(token), jwtToken.accessSecretKey()).getId();
    }
    public String extractAccessNickname(final String token) {
        return extractClaim(subStringToken(token), jwtToken.accessSecretKey()).getSubject();
    }

    public String extractRefreshUserId(final String token) {
        return extractClaim(subStringToken(token), jwtToken.refreshSecretKey()).getId();
    }
    public String extractRefreshNickname(final String token) {
        return extractClaim(subStringToken(token), jwtToken.refreshSecretKey()).getSubject();
    }

    public String generateAccessToken(final String userId,final String nickname) {
        String token = Jwts.builder()
                .setId(userId)
                .setSubject(nickname)
                .claim("role", "ROLE_USER")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtToken.accessExpirationSeconds() * 1000L))
                .signWith(getSignInKey(jwtToken.accessSecretKey()), SignatureAlgorithm.HS256)
                .compact();
        return "Bearer " + token;
    }

    public String generateRefreshToken(final String userId,final String nickname) {
        String token = Jwts.builder()
                .setId(userId)
                .setSubject(nickname)
                .claim("username",nickname)
                .claim("role", "ROLE_USER")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtToken.refreshExpirationSeconds() * 1000L))
                .signWith(getSignInKey(jwtToken.refreshSecretKey()), SignatureAlgorithm.HS256)
                .compact();
        return "Bearer " + token;
    }


    public boolean isAccessTokenValid(final String token) {
        try {
            return !isTokenExpired(subStringToken(token), jwtToken.accessSecretKey());
        } catch (final ExpiredJwtException e) {
            throw new JwtException("Access Token이 만료되었습니다.");
        } catch (final JwtException e) {
            throw new JwtException("Access Token이 유효하지 않습니다.");
        }
    }

    public boolean isRefreshTokenValid(final String token) {
        try {
            return !isTokenExpired(subStringToken(token), jwtToken.refreshSecretKey());
        } catch (final ExpiredJwtException e) {
            throw new JwtException("Refresh Token이 만료되었습니다.");
        } catch (final JwtException e) {
            throw new JwtException("Refresh Token이 유효하지 않습니다.");
        }
    }

    private boolean isTokenExpired(
            final String token,
            final String secretKey
    ) {
        return extractExpiration(token, secretKey).before(new Date());
    }

    private Date extractExpiration(
            final String token,
            final String secretKey
    ) {
        return extractClaim(token, secretKey).getExpiration();
    }

    private Claims extractClaim(
            final String token,
            final String secretKey
    ) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey(secretKey))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey(final String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
