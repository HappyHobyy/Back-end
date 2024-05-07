package org.v1.repository;

import org.v1.model.Community;

import java.util.List;

public interface CommunityRepository {
    public List<Community> readCommunities();
}
