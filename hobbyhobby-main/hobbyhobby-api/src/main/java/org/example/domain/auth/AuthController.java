package org.example.domain.auth;

import lombok.RequiredArgsConstructor;
import org.example.global.config.security.jwt.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {
    private final JwtService jwtService;
    @GetMapping("/api/token/access")
    public ResponseEntity<String> accessToken(
            @RequestHeader("Authorization") final String jwt
    ){
        jwtService.isRefreshTokenValid(jwt);
        final String userId = jwtService.extractRefreshUsername(jwt);
        String accessToken = jwtService.generateAccessToken(userId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",accessToken);
        return ResponseEntity.ok().headers(headers).build();
    }
}
