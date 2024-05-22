package org.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.v1.model.community.Community;

import java.util.List;

public record UserCommunityListResponse(
        @Schema(description = "커뮤니티id", example = "1")
        Integer communityId,
        @Schema(description = "커뮤니티id 이름", example = "야구")
        String communityName
) {
    public static List<UserCommunityListResponse> ofCommunityList(List<Community> communityList) {
        return communityList.stream()
                .map(community -> new UserCommunityListResponse(
                        community.getId(),
                        community.getCommunityName()
                ))
                .toList();
    }
}

