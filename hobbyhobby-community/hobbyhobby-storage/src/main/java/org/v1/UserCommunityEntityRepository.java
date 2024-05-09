package org.v1;

import org.springframework.stereotype.Repository;
import org.v1.repository.UserCommunityRepository;

import java.util.List;

@Repository
public class UserCommunityEntityRepository implements UserCommunityRepository {
    @Override
    public List<CommunityStatusInfo> readUserCommunityList(Long userCommunityId) {
        return null;
    }

    @Override
    public void appendUserToCommunity(Long userId, Long communityId) {

    }

    @Override
    public void removeUserFromCommunity(Long userId, Long communityId) {

    }
}
