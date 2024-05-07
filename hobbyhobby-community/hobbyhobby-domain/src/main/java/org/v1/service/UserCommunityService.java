package org.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementation.CommunityReader;
import org.v1.implementation.ContentReader;
import org.v1.implementation.UserCommunityReader;
import org.v1.model.Community;
import org.v1.model.UserCommunity;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserCommunityService {
    private final UserCommunityReader userCommunityReader;
    private final ContentReader contentReader;
    public void removeUserFromCommunity(Long userId, Long communityId) {

    }
    public void createUserToCommunity(Long userId, Long communityId) {

    }
    public List<UserCommunity> getCommunityList(Long userId) {
        List<Community> userCommunityList = userCommunityReader.readUserCommunity(userId);
        return userCommunityList.stream()
                .map(community -> new UserCommunity(community, contentReader.readTextContentCount(community)))
                .collect(Collectors.toList());
    }
}
