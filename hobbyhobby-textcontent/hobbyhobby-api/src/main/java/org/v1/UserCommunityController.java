package org.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.v1.user.User;
import org.v1.user.UserService;
import response.HttpResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/community")
public class UserCommunityController {
    private final UserService userService;

    @Operation(summary = "테스트", description = "userId 이용하여 자신이 속한 커뮤니티를 조회합니다.")
    @GetMapping("/user/textContent")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<List<Community>> getMyPage(
            @Valid @RequestHeader Long userId
    ) {
        List<Community> communities = userService.getUserCommunity(userId);
        return HttpResponse.success(communities);
    }
}
