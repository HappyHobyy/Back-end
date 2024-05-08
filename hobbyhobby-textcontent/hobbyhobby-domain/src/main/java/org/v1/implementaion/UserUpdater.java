package org.v1.implementaion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.User;
import org.v1.repository.UserRepository;
@AllArgsConstructor
@Component
public class UserUpdater {
    private final UserRepository userRepository;
    public void updateUser(final User user) {
        userRepository.updateUser(user);
    }
}
