package org.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementation.community.UserCommunityAppender;
import org.v1.implementation.community.UserCommunityRemover;

@Service
@AllArgsConstructor
public class UserCommunityService {
    private final UserCommunityRemover userCommunityRemover;
    private final UserCommunityAppender userCommunityAppender;
    public void removeUserFromCommunity(Long userId, Integer communityId) {
        userCommunityRemover.removeUserFromCommunity(userId, communityId);
    }
    public void createUserToCommunity(Long userId, Integer communityId) {
        userCommunityAppender.appendUserToCommunity(userId, communityId);
    }
}
