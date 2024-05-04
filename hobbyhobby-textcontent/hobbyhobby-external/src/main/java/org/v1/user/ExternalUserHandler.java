package org.v1.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.Community;

import java.util.List;

@Component
@AllArgsConstructor
public class ExternalUserHandler {
    private ExternalUserClient externalUserClient;
    public List<Community> getUserCommunity(Long userId) {
        return externalUserClient.getResource(new UserCommunityRequest(userId)).toCommunityList();
    }
}