package org.v1.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.v1.domain.user.domain.User;
import org.v1.domain.user.service.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("/external/user")
public class UserApiController {
    private final UserService userService;

    @GetMapping("")
    public UserProfileResponse getMyPage(
            @RequestHeader Long userId
    ) {
        User user = userService.getMyPage(userId);
        return UserProfileResponse.ofUser(user);
    }
}
