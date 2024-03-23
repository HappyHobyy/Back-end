package org.example.domain.user.implemetion;

import lombok.RequiredArgsConstructor;
import org.example.domain.user.domain.User;
import org.example.domain.user.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAppender {
    private final UserRepository userRepository;
    public User append(
            final User user
    ){
        return userRepository.save(user);
    }
}
