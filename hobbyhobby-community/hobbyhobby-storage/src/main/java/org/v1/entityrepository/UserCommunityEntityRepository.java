package org.v1.entityrepository;

import org.springframework.stereotype.Repository;
import org.v1.repository.UserCommunityRepository;

@Repository
public class UserCommunityEntityRepository implements UserCommunityRepository {

    @Override
    public void appendUserToCommunity(Long userId, Integer communityId) {

    }

    @Override
    public void removeUserFromCommunity(Long userId, Integer communityId) {

    }
}