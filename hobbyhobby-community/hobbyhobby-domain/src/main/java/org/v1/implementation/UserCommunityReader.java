package org.v1.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.Community;
import org.v1.model.UserCommunity;
import org.v1.repository.UserCommunityRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class UserCommunityReader {
    private final UserCommunityRepository userCommunityRepository;
    public List<Community> readUserCommunity(final Long userId) {
        return userCommunityRepository.readUserCommunityList(userId);
    }
}
