package org.v1.repository;

import org.v1.model.Community;
import org.v1.model.CommunityStatusInfo;

import java.util.List;

public interface UserCommunityRepository {
    List<CommunityStatusInfo> readUserCommunityList(Long userCommunityId);
    void appendUserToCommunity(Long userId, Long communityId);
    void removeUserFromCommunity(Long userId, Long communityId);
}
