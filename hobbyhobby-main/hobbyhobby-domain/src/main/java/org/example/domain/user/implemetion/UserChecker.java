package org.example.domain.user.implemetion;

import lombok.RequiredArgsConstructor;
import org.example.domain.user.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserChecker {

    private final UserRepository userRepository;

    public boolean checkUserEmailDuplication(final String email) {
        return userRepository.existsUserByEmail(email);
    }
}
