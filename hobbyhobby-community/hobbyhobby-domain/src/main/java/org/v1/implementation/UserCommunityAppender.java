package org.v1.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.repository.UserCommunityRepository;

@Component
@AllArgsConstructor
public class UserCommunityAppender {
    private final UserCommunityRepository userCommunityRepository;
    public void appendUserToCommunity(final Long userId, final Long communityId) {
        userCommunityRepository.appendUserToCommunity(userId, communityId);
    }
}
