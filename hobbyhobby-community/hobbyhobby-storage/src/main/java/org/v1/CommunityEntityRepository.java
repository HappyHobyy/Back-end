package org.v1;

import org.springframework.stereotype.Repository;
import org.v1.model.Community;
import org.v1.model.UserCommunity;
import org.v1.repository.CommunityRepository;

import java.util.List;

@Repository
public class CommunityEntityRepository implements CommunityRepository {

    @Override
    public List<UserCommunity> readUserCommunities(Long userId) {
        return null;
    }

    @Override
    public List<Community> readPopularCommunities() {
        return null;
    }

    @Override
    public Community readPopulistCommunity() {
        return null;
    }
}
