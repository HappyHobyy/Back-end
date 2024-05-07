package org.v1.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.Community;
import org.v1.repository.CommunityRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class CommunityReader {
    private final CommunityRepository communityRepository;
    public List<Community> getCommunities() {
        return communityRepository.readCommunities();
    }
}
