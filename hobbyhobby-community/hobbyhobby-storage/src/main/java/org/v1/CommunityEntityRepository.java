package org.v1;

import org.springframework.stereotype.Repository;
import org.v1.model.Community;
import org.v1.repository.CommunityRepository;

import java.util.List;

@Repository
public class CommunityEntityRepository implements CommunityRepository {

    @Override
    public List<Community> readCommunities() {
        return null;
    }
}
