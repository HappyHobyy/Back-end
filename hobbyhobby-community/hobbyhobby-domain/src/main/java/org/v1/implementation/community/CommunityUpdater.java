package org.v1.implementation.community;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.like.Like;
import org.v1.repository.CommunityRepository;

@Component
@AllArgsConstructor
public class CommunityUpdater {
    private final CommunityRepository communityRepository;
    public void updateCommunityLike(final Like like) {
        communityRepository.updateCommunityLike(like);
    }
}
