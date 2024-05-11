package org.v1.implementation;

import lombok.RequiredArgsConstructor;
import org.v1.model.User;
import org.v1.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAppender {
    private final UserRepository userRepository;
    public User appendUser(
            final User user
    ){
        return userRepository.appendUser(user).orElseThrow();
    }
    public void updateUser(
            final User user
    ){
        userRepository.update(user);
    }
}
