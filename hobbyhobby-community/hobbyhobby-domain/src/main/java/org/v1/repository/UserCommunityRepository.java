package org.v1.repository;

import org.v1.model.Community;
import org.v1.model.UserCommunity;

import java.util.List;

public interface UserCommunityRepository {
    List<Community> readUserCommunityList(Long userCommunityId);
}
