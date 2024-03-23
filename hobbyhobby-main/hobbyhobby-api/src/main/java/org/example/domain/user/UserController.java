package org.example.domain.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.domain.user.domain.User;
import org.example.domain.user.dto.UserLoginRequest;
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
        userService.defaultRegister(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login/default")
    public ResponseEntity<Object> defaultLogin(
            @Valid @RequestBody UserLoginRequest request
    ) {
        User user = request.toEntity();
        User savedUser = userService.defaultLogin(user);
        String refreshToken = jwtService.generateRefreshToken(String.valueOf(savedUser.getId()));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", refreshToken);
        return ResponseEntity.ok().headers(headers).build();
    }
}
