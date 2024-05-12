package org.v1.dto;

import org.v1.model.UserCommunity;

import java.util.List;

public record UserCommunityListResponse(
        Integer communityId,
        String communityName,
        String imageUrl,
        Integer userHistoryCount
) {
    public static List<UserCommunityListResponse> ofCommunityList(List<UserCommunity> communityList) {
        return communityList.stream()
                .map(community -> new UserCommunityListResponse(
                        community.getCommunity().getId(),
                        community.getCommunity().getCommunityName(),
                        community.getCommunity().getImageUrl(),
                        community.getStatus().userHistoryCount()
                ))
                .toList();
    }
}

