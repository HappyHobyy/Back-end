package org.v1.repository;

import org.v1.model.Like;

public interface CommunityRepository {
    void plusCommunityLikes( final Integer targetCommunityId);
    void minusCommunityLikes(final Integer targetCommunityId);
    void resetCommunityLikes();
    Integer readPopulistCommunity();
}
