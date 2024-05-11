package org.v1.implementaion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.repository.UserRepository;

@Component
@AllArgsConstructor
public class UserRemover {
    private final UserRepository userRepository;
    public void removeUser(final Long userId) {
        userRepository.removeUser(userId);
    }
}
