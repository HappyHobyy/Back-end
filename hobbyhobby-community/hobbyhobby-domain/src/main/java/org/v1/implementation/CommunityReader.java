package org.v1.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.Community;
import org.v1.model.CommunityStatusInfo;
import org.v1.repository.CommunityRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class CommunityReader {
    private final CommunityRepository communityRepository;
    public List<CommunityStatusInfo> readPopularCommunities(Long userId) {
        return communityRepository.readPopularCommunities(userId);
    }
    public List<CommunityStatusInfo> readUserCommunities(Long userId) {
        return communityRepository.readUserCommunities(userId);
    }
    public Community readPopulistCommunity() {
        return communityRepository.readPopulistCommunity();
    }
}
