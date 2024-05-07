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

@Tag(name = "userCommunity", description = "유저 커뮤니티 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/community")
public class UserCommunityController {
    private final UserCommunityService userCommunityService;

    @GetMapping("")
    @Operation(summary = "유저 즐겨찾기 커뮤니티 가져오기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> removeUser(
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        userCommunityService.getCommunityList(userId);
        return HttpResponse.successOnly();
    }
}
