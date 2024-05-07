package org.v1;

import org.springframework.stereotype.Repository;
import org.v1.model.Community;
import org.v1.model.UserCommunity;
import org.v1.repository.UserCommunityRepository;

import java.util.List;

@Repository
public class UserCommunityEntityRepository implements UserCommunityRepository {
    @Override
    public List<Community> readUserCommunityList(Long userCommunityId) {
        return null;
    }
}
