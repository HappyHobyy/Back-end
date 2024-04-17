package org.v1.domain.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.v1.domain.auth.dto.request.UserDefaultLoginRequest;
import org.v1.domain.auth.dto.request.UserOAuthLoginRequest;
import org.v1.domain.auth.service.AuthService;
import org.v1.domain.user.domain.User;
import org.v1.domain.auth.dto.request.UserRegisterRequest;
import org.v1.global.config.security.jwt.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
@Tag(name = "auth", description = "인증 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {
    private final JwtService jwtService;
    private final AuthService authService;
    @GetMapping("/token/access")
    @Operation(summary = "Access토큰 재발급")
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

    @PostMapping("/user/register")
    @Operation(summary = "회원가입")
    public ResponseEntity<String> defaultRegister(
            @Valid @RequestBody UserRegisterRequest request
    ) {
        User user = request.toEntity();
        authService.registerUser(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/login/default")
    @Operation(summary = "일반 유저 로그인")
    public ResponseEntity<Object> defaultLogin(
            @Valid @RequestBody UserDefaultLoginRequest request
    ) {
        User user = request.toEntity();
        User savedUser = authService.loginUser(user);
        String refreshToken = jwtService.generateRefreshToken(String.valueOf(savedUser.getId()));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", refreshToken);
        return ResponseEntity.ok().headers(headers).build();
    }

    @PostMapping("/user/login/kakao")
    @Operation(summary = "카카오 유저 로그인")
    public ResponseEntity<Object> kakaoLogin(
            @Valid @RequestBody UserOAuthLoginRequest request
    ) {
        User user = request.toKakaoEntity();
        User savedUser = authService.loginUserWithKakao(user);
        String refreshToken = jwtService.generateRefreshToken(String.valueOf(savedUser.getId()));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", refreshToken);
        return ResponseEntity.ok().headers(headers).build();
    }

    @PostMapping("/user/login/google")
    @Operation(summary = "구글 유저 로그인")
    public ResponseEntity<Object> googleLogin(
            @Valid @RequestBody UserOAuthLoginRequest request
    ) {
        User user = request.toGoogleEntity();
        User savedUser = authService.loginUserWithGoogle(user);
        String refreshToken = jwtService.generateRefreshToken(String.valueOf(savedUser.getId()));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", refreshToken);
        return ResponseEntity.ok().headers(headers).build();
    }
}
