package org.v1.implementaion.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.User;
import org.v1.repository.UserRepository;
@AllArgsConstructor
@Component
public class UserAppender {
    private final UserRepository userRepository;
    public void appendUser(final User user){
        userRepository.appendUser(user);
    }
}
