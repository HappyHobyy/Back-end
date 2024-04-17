package org.v1.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.v1.domain.user.dto.request.UserResetPasswordRequest;
import org.v1.domain.user.dto.response.UserGetMyPageResponse;
import org.v1.domain.user.service.EmailService;
import org.v1.domain.user.service.UserService;
import org.v1.domain.user.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "user", description = "회원 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final EmailService emailService;
    @DeleteMapping("/delete")
    @Operation(summary = "유저 정보 삭제")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public ResponseEntity<Object> removeUser(
            HttpServletRequest request
    ) {
        Long userId = Long.valueOf(request.getHeader("userId"));
        userService.removeUser(userId);
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "마이페이지 조회", description = "userId 이용하여 마이페이지를 조회합니다.")
    @GetMapping("/mypage")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public ResponseEntity<UserGetMyPageResponse> getMyPage(
            HttpServletRequest request
    ) {
        User user = userService.getMyPage(Long.valueOf(request.getHeader("userId")));
        UserGetMyPageResponse response =UserGetMyPageResponse.from(user);
        return ResponseEntity.ok(response);
    }
    @Operation(summary = "비밀번호 초기화", description = "email 이용하여 마이페이지를 조회합니다.")
    @PostMapping("/resetPassword")
    public ResponseEntity<String> sendEmail(
            @Valid @RequestBody UserResetPasswordRequest request
            ){
        emailService.sendEmailAndChangePassword(request.email());
        return ResponseEntity.ok().build();
    }
}
