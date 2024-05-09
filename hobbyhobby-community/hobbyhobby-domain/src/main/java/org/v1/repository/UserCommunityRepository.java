package org.v1.repository;

import java.util.List;

public interface UserCommunityRepository {
    void appendUserToCommunity(Long userId, Long communityId);
    void removeUserFromCommunity(Long userId, Long communityId);
}
