package org.v1.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.v1.domain.user.dto.response.UserGetMyPageResponse;
import org.v1.domain.user.service.UserService;
import org.v1.domain.user.domain.User;
import org.v1.domain.user.dto.request.UserDefaultLoginRequest;
import org.v1.domain.user.dto.request.UserOAuthLoginRequest;
import org.v1.domain.user.dto.request.UserRegisterRequest;
import org.v1.global.config.security.jwt.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "user", description = "회원 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/register")
    @Operation(summary = "회원가입")
    public ResponseEntity<String> defaultRegister(
            @Valid @RequestBody UserRegisterRequest request
    ) {
        User user = request.toEntity();
        userService.registerUser(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login/default")
    @Operation(summary = "일반 유저 로그인")
    public ResponseEntity<Object> defaultLogin(
            @Valid @RequestBody UserDefaultLoginRequest request
    ) {
        User user = request.toEntity();
        User savedUser = userService.loginUser(user);
        String refreshToken = jwtService.generateRefreshToken(String.valueOf(savedUser.getId()));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", refreshToken);
        return ResponseEntity.ok().headers(headers).build();
    }
    @PostMapping("/login/kakao")
    @Operation(summary = "카카오 유저 로그인")
    public ResponseEntity<Object> kakaoLogin(
            @Valid @RequestBody UserOAuthLoginRequest request
    ) {
        User user = request.toKakaoEntity();
        User savedUser = userService.loginUserWithKakao(user);
        String refreshToken = jwtService.generateRefreshToken(String.valueOf(savedUser.getId()));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", refreshToken);
        return ResponseEntity.ok().headers(headers).build();
    }

    @PostMapping("/login/google")
    @Operation(summary = "구글 유저 로그인")
    public ResponseEntity<Object> googleLogin(
            @Valid @RequestBody UserOAuthLoginRequest request)
    {
        User user = request.toGoogleEntity();
        User savedUser = userService.loginUserWithGoogle(user);
        String refreshToken = jwtService.generateRefreshToken(String.valueOf(savedUser.getId()));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", refreshToken);
        return ResponseEntity.ok().headers(headers).build();
    }
    @DeleteMapping("/delete")
    @Operation(summary = "유저 정보 삭제")
    public ResponseEntity<Object> removeUser(
            HttpServletRequest request
    ) {
        Long userId = Long.valueOf(request.getHeader("userId"));
        userService.removeUser(userId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "마이페이지 조회", description = "userId 이용하여 마이페이지를 조회합니다.")
    @GetMapping("/mypage")
    public ResponseEntity<UserGetMyPageResponse> getMyPage(
            HttpServletRequest request
    ) {
        User user = userService.getMyPage(Long.valueOf(request.getHeader("userId")));
        UserGetMyPageResponse response =UserGetMyPageResponse.from(user);
        return ResponseEntity.ok(response);
    }
}
