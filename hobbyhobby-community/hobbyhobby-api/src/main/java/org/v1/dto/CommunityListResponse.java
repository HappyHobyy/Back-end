package org.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.v1.model.community.Community;

import java.util.List;

public record CommunityListResponse(
        @Schema(description = "커뮤니티id", example = "1")
        Integer communityId,
        @Schema(description = "커뮤니티id 이름", example = "야구")
        String communityName
) {
    public static List<CommunityListResponse> ofCommunityList(List<Community> communityList) {
        return communityList.stream()
                .map(community -> new CommunityListResponse(
                        community.getId(),
                        community.getCommunityName()
                ))
                .toList();
    }
}
