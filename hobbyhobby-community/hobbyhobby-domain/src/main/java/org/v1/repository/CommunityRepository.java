package org.v1.repository;

import org.v1.model.community.Community;
import org.v1.model.like.Like;

import java.util.List;

public interface CommunityRepository {
    List<Community> readUserCommunities(Long userId);
    List<Community> readPopularCommunities();
    List<Community> readRecommendCommunities(Long userId);
    void updateCommunityLike(Like like);
}
