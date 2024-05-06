package org.v1.implementaion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.User;
import org.v1.repository.UserRepository;

@Component
@AllArgsConstructor
public class UserReader {
    private final UserRepository userRepository;
    public User readUser(final Long userId) {
        return userRepository.readUser(userId);
    }
}
