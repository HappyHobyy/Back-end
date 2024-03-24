package org.example.domain.user.implementation;

import lombok.RequiredArgsConstructor;
import org.example.domain.user.domain.User;
import org.example.domain.user.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAppender {
    private final UserRepository userRepository;
    public User appendUser(
            final User user
    ){
        return userRepository.save(user);
    }
}
