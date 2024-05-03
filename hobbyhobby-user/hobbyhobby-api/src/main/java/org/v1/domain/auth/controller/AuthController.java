package org.v1.domain.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.v1.domain.auth.AuthService;
import org.v1.domain.auth.dto.request.EmailCheckRequest;
import org.v1.domain.auth.dto.request.NicknameCheckRequest;
import org.v1.domain.auth.dto.request.UserLoginRequest;
import org.v1.domain.auth.dto.response.OnlyAccessTokenResponse;
import org.v1.domain.auth.dto.response.TokenResponse;
import org.v1.domain.user.domain.User;
import org.v1.domain.auth.dto.request.UserRegisterRequest;
import org.v1.global.config.security.jwt.JwtService;
import response.HttpResponse;

@Tag(name = "auth", description = "인증 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {
    private final JwtService jwtService;
    private final AuthService authService;
    @GetMapping("/auth/token/access")
    @Operation(summary = "Access토큰 재발급")
    @Parameter(name = "Authorization", description = "Refresh token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<OnlyAccessTokenResponse> accessToken(
            @RequestHeader("Authorization") final String jwt
    ){
        jwtService.isRefreshTokenValid(jwt);
        final String userId = jwtService.extractRefreshUsername(jwt);
        String accessToken = jwtService.generateAccessToken(userId);
        OnlyAccessTokenResponse response= OnlyAccessTokenResponse.from(accessToken);
        return HttpResponse.success(response);
    }
    @PostMapping("/auth/register/default")
    @Operation(summary = "일반 유저 회원가입")
    public HttpResponse<Object> defaultRegister(
            @Valid @RequestBody UserRegisterRequest.DefaultRegister request
    ) {
        User user = request.toUser();
        authService.registerDefaultUser(user);
        return HttpResponse.successOnly();
    }
    @PostMapping("/auth/register/oAuth")
    @Operation(summary = "소셜 유저 회원가입")
    public HttpResponse<Object> oAuthRegister(
            @Valid @RequestBody UserRegisterRequest.OAuthRegister request
    ) {
        User user = request.toUser();
        authService.registerOAuthUser(user);
        return HttpResponse.successOnly();
    }

    @PostMapping("/auth/login/default")
    @Operation(summary = "일반 유저 로그인")
    public HttpResponse<TokenResponse> defaultLogin(
            @Valid @RequestBody UserLoginRequest.DefaultLogin request
    ) {
        User user = request.toUser();
        User savedUser = authService.loginDefaultUser(user);
        String refreshToken = jwtService.generateRefreshToken(String.valueOf(savedUser.getId()));
        String accessToken = jwtService.generateAccessToken(String.valueOf(savedUser.getId()));
        return HttpResponse.success(TokenResponse.from(refreshToken,accessToken));
    }
    @PostMapping("/auth/login/oAuth")
    @Operation(summary = "소셜 유저 로그인")
    public HttpResponse<TokenResponse> oAuthLogin(
            @Valid @RequestBody UserLoginRequest.OAuthLogin request
    ) {
        User user = request.toUser();
        User savedUser = authService.loginOAuthUser(user);
        String refreshToken = jwtService.generateRefreshToken(String.valueOf(savedUser.getId()));
        String accessToken = jwtService.generateAccessToken(String.valueOf(savedUser.getId()));
        return HttpResponse.success(TokenResponse.from(refreshToken,accessToken));
    }
    @PostMapping("/auth/register/email")
    @Operation(summary = "이메일 중복 확인")
    public HttpResponse<Object> checkEmail(
            @Valid @RequestBody EmailCheckRequest request
    ) {
        User user = request.toUser();
        authService.checkEmailDuplicate(user);
        return HttpResponse.successOnly();
    }
    @PostMapping("/auth/register/nickName")
    @Operation(summary = "닉네임 중복 확인")
    public HttpResponse<Object> checkNickname(
            @Valid @RequestBody NicknameCheckRequest request
    ) {
        User user = request.toUser();
        authService.checkNicknameDuplicate(user);
        return HttpResponse.successOnly();
    }
}