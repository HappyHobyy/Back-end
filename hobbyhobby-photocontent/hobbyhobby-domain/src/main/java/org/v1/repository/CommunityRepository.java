package org.v1.repository;

public interface CommunityRepository {
    void plusCommunityLikes( final Integer targetCommunityId);
    void minusCommunityLikes(final Integer targetCommunityId);
    void resetCommunityLikes();
    Integer readPopulistCommunity();
}
