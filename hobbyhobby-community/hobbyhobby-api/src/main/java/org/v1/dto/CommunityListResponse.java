package org.v1.dto;

import org.v1.model.Community;

import java.util.List;

public record CommunityListResponse(
        Integer communityId,
        String communityName,
        String imageUrl
) {
    public static List<CommunityListResponse> ofCommunityList(List<Community> communityList) {
        return communityList.stream()
                .map(community -> new CommunityListResponse(
                        community.getId(),
                        community.getCommunityName(),
                        community.getImageUrl()
                ))
                .toList();
    }
}
