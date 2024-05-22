package org.v1.repository.community;

public interface CommunityRepository {
    void plusCommunityLikes( final Integer targetCommunityId);
    void minusCommunityLikes(final Integer targetCommunityId);
    void resetCommunityLikes();
    int readPopulistCommunity();
}
