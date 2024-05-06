package org.v1.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.User;
import org.v1.repository.UserRepository;

@Component
@AllArgsConstructor
public class ExternalUserHandler implements UserRepository {
    private ExternalUserClient externalUserClient;

    @Override
    public User readUser(Long userId) {
        return externalUserClient.getUser(new UserRequest(userId)).toUser();
    }
}
