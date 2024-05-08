package org.v1.implementation;

import lombok.RequiredArgsConstructor;
import org.v1.model.User;
import org.v1.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAppender {
    private final UserRepository userRepository;
    public void appendUser(
            final User user
    ){
        userRepository.save(user);
    }
    public void updateUser(
            final User user
    ){
        userRepository.update(user);
    }
}
