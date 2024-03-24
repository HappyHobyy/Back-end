package org.example.domain.user.implementation;

import lombok.RequiredArgsConstructor;
import org.example.domain.user.domain.User;
import org.example.domain.user.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserChecker {

    private final UserRepository userRepository;

    public boolean isUserEmailDuplicate(final User user) {
        return userRepository.existsUserByEmailAndType(user.getEmail(), user.getType());
    }
}
