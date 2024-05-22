package org.v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.v1.service.UserCommunityService;
import response.HttpResponse;

@Tag(name = "Community", description = "유저 커뮤니티 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/community/user")
public class UserCommunityController {
    private final UserCommunityService userCommunityService;
    @PostMapping("")
    @Operation(summary = "유저 커뮤니티에 즐겨찾기하기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> createUserCommunity(
            @Parameter(hidden = true) @Valid @RequestHeader Long userId,
            @RequestBody UserCommunityRequest userCommunityRequest
    ) {
        userCommunityService.createUserToCommunity(userId,userCommunityRequest.communityId());
        return HttpResponse.successOnly();
    }
    @DeleteMapping("")
    @Operation(summary = "유저 커뮤니티에서 즐겨찾기 해제하기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> removeUserCommunity(
            @Parameter(hidden = true) @Valid @RequestHeader Long userId,
            @RequestBody UserCommunityRequest userCommunityRequest
    ) {
        userCommunityService.removeUserFromCommunity(userId,userCommunityRequest.communityId());
        return HttpResponse.successOnly();
    }
}
