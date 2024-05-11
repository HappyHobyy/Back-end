package org.v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.v1.service.AuthService;
import org.v1.dto.request.EmailCheckRequest;
import org.v1.dto.request.NicknameCheckRequest;
import org.v1.dto.request.UserLoginRequest;
import org.v1.dto.response.OnlyAccessTokenResponse;
import org.v1.dto.response.TokenResponse;
import org.v1.service.EmailService;
import org.v1.model.User;
import org.v1.dto.request.UserRegisterRequest;
import org.v1.dto.request.UserResetPasswordRequest;
import org.v1.global.config.security.jwt.JwtService;
import response.HttpResponse;

@Tag(name = "auth", description = "인증 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtService jwtService;
    private final AuthService authService;
    private final EmailService emailService;
    @GetMapping("/auth/token/access")
    @Operation(summary = "Access토큰 재발급")
    @Parameter(name = "Authorization", description = "Refresh token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<OnlyAccessTokenResponse> accessToken(
            @RequestHeader("Authorization") final String jwt
    ){
        jwtService.isRefreshTokenValid(jwt);
        final String userId = jwtService.extractRefreshUserId(jwt);
        final String nickname = jwtService.extractRefreshNickname(jwt);
        String accessToken = jwtService.generateAccessToken(userId,nickname);
        OnlyAccessTokenResponse response= OnlyAccessTokenResponse.from(accessToken);
        return HttpResponse.success(response);
    }
    @PostMapping("/register/default")
    @Operation(summary = "일반 유저 회원가입")
    public HttpResponse<Object> defaultRegister(
            @Valid @RequestBody UserRegisterRequest.DefaultRegister request
    ) {
        User user = request.toUser();
        authService.registerDefaultUser(user);
        return HttpResponse.successOnly();
    }
    @PostMapping("/register/oAuth")
    @Operation(summary = "소셜 유저 회원가입")
    public HttpResponse<Object> oAuthRegister(
            @Valid @RequestBody UserRegisterRequest.OAuthRegister request
    ) {
        User user = request.toUser();
        authService.registerOAuthUser(user);
        return HttpResponse.successOnly();
    }

    @PostMapping("/login/default")
    @Operation(summary = "일반 유저 로그인")
    public HttpResponse<TokenResponse> defaultLogin(
            @Valid @RequestBody UserLoginRequest.DefaultLogin request
    ) {
        User user = request.toUser();
        User savedUser = authService.loginDefaultUser(user);
        String refreshToken = jwtService.generateRefreshToken(String.valueOf(savedUser.getId().value()),savedUser.getNickname());
        String accessToken = jwtService.generateAccessToken(String.valueOf(savedUser.getId().value()), savedUser.getNickname());
        return HttpResponse.success(TokenResponse.from(refreshToken,accessToken));
    }
    @PostMapping("/login/oAuth")
    @Operation(summary = "소셜 유저 로그인")
    public HttpResponse<TokenResponse> oAuthLogin(
            @Valid @RequestBody UserLoginRequest.OAuthLogin request
    ) {
        User user = request.toUser();
        User savedUser = authService.loginOAuthUser(user);
        String refreshToken = jwtService.generateRefreshToken(String.valueOf(savedUser.getId().value()),savedUser.getNickname());
        String accessToken = jwtService.generateAccessToken(String.valueOf(savedUser.getId().value()),savedUser.getNickname());
        return HttpResponse.success(TokenResponse.from(refreshToken,accessToken));
    }
    @PostMapping("/register/email")
    @Operation(summary = "이메일 중복 확인")
    public HttpResponse<Object> checkEmail(
            @Valid @RequestBody EmailCheckRequest request
    ) {
        User user = request.toUser();
        authService.checkEmailDuplicate(user);
        return HttpResponse.successOnly();
    }
    @PostMapping("/register/nickName")
    @Operation(summary = "닉네임 중복 확인")
    public HttpResponse<Object> checkNickname(
            @Valid @RequestBody NicknameCheckRequest request
    ) {
        User user = request.toUser();
        authService.checkNicknameDuplicate(user);
        return HttpResponse.successOnly();
    }
    @Operation(summary = "비밀번호 초기화", description = "email 이용하여 마이페이지를 조회합니다.")
    @PostMapping("/resetPassword")
    public HttpResponse<Object> sendEmail(
            @Valid @RequestBody UserResetPasswordRequest request
    ){
        emailService.sendEmailAndChangePassword(request.email());
        return HttpResponse.successOnly();
    }
}