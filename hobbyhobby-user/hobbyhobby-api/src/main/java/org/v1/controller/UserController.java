package org.v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import org.v1.dto.response.UserGetMyPageResponse;
import org.v1.model.User;
import org.springframework.web.bind.annotation.*;
import org.v1.service.UserService;
import response.HttpResponse;

import java.io.File;
import java.io.IOException;

import static org.v1.global.util.FileUtil.convertMultipartFileToFile;

@Tag(name = "user", description = "회원 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    @DeleteMapping("/delete")
    @Operation(summary = "유저 정보 삭제")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> removeUser(
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        userService.removeUser(userId);
        return HttpResponse.successOnly();
    }
    @Operation(summary = "마이페이지 조회", description = "userId 이용하여 마이페이지를 조회합니다.")
    @GetMapping("/mypage")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<UserGetMyPageResponse> getMyPage(
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        User user = userService.getMyPage(userId);
        UserGetMyPageResponse response =UserGetMyPageResponse.of(user);
        return HttpResponse.success(response);
    }
    @Operation(summary = "유저 프로필 사진 변경 혹은 추가", description = "")
    @PostMapping("/profileImage")
    public HttpResponse<Object> changeProfileImage(
            @RequestPart("file") MultipartFile file,
            @RequestHeader("userId") Long userId
    ) {
        try {
            File convertedFile = convertMultipartFileToFile(file);
            userService.changeProfileImage(convertedFile, userId);
        } catch (IOException e) {
            throw new RuntimeException("MultipartFile을 File로 변환하는데 실패했습니다.", e);
        }
        return HttpResponse.successOnly();
    }
}
