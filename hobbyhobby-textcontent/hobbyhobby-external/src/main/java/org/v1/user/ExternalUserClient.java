package org.v1.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service")
public interface ExternalUserClient {
    @GetMapping("/user/community")
    UserCommunityResponse getResource(@RequestHeader(value = "userId")   UserCommunityRequest userCommunityRequest);
}