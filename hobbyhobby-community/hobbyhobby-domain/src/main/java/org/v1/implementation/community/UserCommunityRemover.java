package org.v1.implementation.community;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.repository.UserCommunityRepository;

@Component
@AllArgsConstructor
public class UserCommunityRemover {
    private final UserCommunityRepository userCommunityRepository;
    public void removeUserFromCommunity(Long userId, Integer communityId) {
        userCommunityRepository.removeUserFromCommunity(userId, communityId);
    }
}
