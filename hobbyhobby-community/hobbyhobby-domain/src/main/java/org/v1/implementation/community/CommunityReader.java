package org.v1.implementation.community;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.community.Community;
import org.v1.model.community.UserCommunity;
import org.v1.repository.CommunityRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class CommunityReader {
    private final CommunityRepository communityRepository;
    public List<Community> readPopularCommunities() {
        return communityRepository.readPopularCommunities();
    }
    public List<Community> readUserCommunities(Long userId) {
        return communityRepository.readUserCommunities(userId);
    }
    public Community readPopulistCommunity() {
        return communityRepository.readPopulistCommunity();
    }
}
