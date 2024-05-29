package org.v1.implementation.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.user.User;
import org.v1.repository.UserRepository;

@Component
@AllArgsConstructor
public class UserManager {
    private final UserRepository userRepository;
    public void appendUser(final User user){
        userRepository.appendUser(user);
    }
    public void removeUser(final Long userId) {
        userRepository.removeUser(userId);
    }
    public void updateUser(final User user) {
        userRepository.updateUser(user);
    }
}
