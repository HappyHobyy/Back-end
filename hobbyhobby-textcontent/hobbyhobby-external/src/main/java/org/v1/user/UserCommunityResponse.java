package org.v1.user;

import org.v1.model.Community;

import java.util.List;
import java.util.stream.Collectors;

public record UserCommunityResponse(List<UserCommunityInfo> userCommunities) {
    public record UserCommunityInfo(Long communityId, String communityName) {}

    public List<Community> toCommunityList() {
        return userCommunities.stream()
                .map(info -> new Community(info.communityId(), info.communityName()))
                .collect(Collectors.toList());
    }
}