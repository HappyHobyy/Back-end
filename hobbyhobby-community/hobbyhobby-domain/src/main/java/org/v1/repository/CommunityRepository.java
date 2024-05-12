package org.v1.repository;

import org.v1.model.community.Community;
import org.v1.model.community.UserCommunity;

import java.util.List;

public interface CommunityRepository {
    List<UserCommunity> readUserCommunities(Long userId);
    List<Community> readPopularCommunities();
    Community readPopulistCommunity();
}
