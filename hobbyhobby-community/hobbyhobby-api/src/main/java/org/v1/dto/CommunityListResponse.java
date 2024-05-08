package org.v1.dto;

import org.v1.model.Community;
import org.v1.model.CommunityStatusInfo;

import java.util.List;

public record CommunityListResponse(
        Long communityId,
        String communityName,
        String imageUrl,
        Integer userHistoryCount
) {
    public static List<CommunityListResponse> ofUserCommunityList(List<CommunityStatusInfo> communityList) {
        return communityList.stream()
                .map(communityStatusInfo -> new CommunityListResponse(
                        communityStatusInfo.getCommunity().getId(),
                        communityStatusInfo.getCommunity().getCommunityName(),
                        communityStatusInfo.getCommunity().getImageUrl(),
                        communityStatusInfo.getUserStatus().userHistoryCount())
                )
                .toList();
    }
}
