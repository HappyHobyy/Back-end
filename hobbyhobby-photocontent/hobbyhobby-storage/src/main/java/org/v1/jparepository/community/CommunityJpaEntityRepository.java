package org.v1.jparepository.community;

import org.springframework.stereotype.Repository;
import org.v1.repository.community.CommunityRepository;
@Repository
public class CommunityJpaEntityRepository implements CommunityRepository
{

    @Override
    public void plusCommunityLikes(Integer targetCommunityId) {

    }

    @Override
    public void minusCommunityLikes(Integer targetCommunityId) {

    }

    @Override
    public void resetCommunityLikes() {

    }

    @Override
    public Integer readPopulistCommunity() {
        return null;
    }
}