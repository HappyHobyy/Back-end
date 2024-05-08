package org.v1.repository;

import org.v1.model.Community;
import org.v1.model.CommunityStatusInfo;

import java.util.List;

public interface CommunityRepository {
    List<CommunityStatusInfo> readUserCommunities(Long userId);
    List<CommunityStatusInfo> readPopularCommunities(Long userId);
    Community readPopulistCommunity();
}
