package org.v1.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.Community;

import java.util.List;

@Component
@AllArgsConstructor
public class ExternalUserHandler implements UserHandler {
    private ExternalUserClient externalUserClient;
    @Override
    public List<Community> getUserCommunity(Long userId) {
        return externalUserClient.getResource(new UserCommunityRequest(userId)).toCommunityList();
    }
}
