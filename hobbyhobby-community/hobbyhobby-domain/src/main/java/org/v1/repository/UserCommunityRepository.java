package org.v1.repository;

import java.util.List;

public interface UserCommunityRepository {
    void appendUserToCommunity(Long userId, Integer communityId);
    void removeUserFromCommunity(Long userId, Integer communityId);
}
