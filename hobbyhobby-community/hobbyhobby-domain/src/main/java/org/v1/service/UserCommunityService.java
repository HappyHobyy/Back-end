package org.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementation.UserCommunityAppender;
import org.v1.implementation.UserCommunityRemover;

@Service
@AllArgsConstructor
public class UserCommunityService {
    private final UserCommunityRemover userCommunityRemover;
    private final UserCommunityAppender userCommunityAppender;
    public void removeUserFromCommunity(Long userId, Long communityId) {
        userCommunityRemover.removeUserFromCommunity(userId, communityId);
    }
    public void createUserToCommunity(Long userId, Long communityId) {
        userCommunityAppender.appendUserToCommunity(userId, communityId);
    }
}
