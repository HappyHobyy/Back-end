package org.example.domain.user.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.domain.user.service.UserService;
import org.example.domain.user.domain.User;
import org.example.domain.user.dto.UserDefaultLoginRequest;
import org.example.domain.user.dto.UserOAuthLoginRequest;
import org.example.domain.user.dto.UserRegisterRequest;
import org.example.global.config.security.jwt.JwtService;
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
    public ResponseEntity<String> defaultRegister(
            @Valid @RequestBody UserRegisterRequest request
    ) {
        User user = request.toEntity();
        userService.registerUser(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login/default")
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
    public ResponseEntity<Object> googleLogin(
            @Valid @RequestBody UserOAuthLoginRequest request
    ) {
        User user = request.toGoogleEntity();
        User savedUser = userService.loginUserWithGoogle(user);
        String refreshToken = jwtService.generateRefreshToken(String.valueOf(savedUser.getId()));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", refreshToken);
        return ResponseEntity.ok().headers(headers).build();
    }
}
