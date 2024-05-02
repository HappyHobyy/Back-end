package org.v1.domain.user.implementation;

import lombok.RequiredArgsConstructor;
import org.v1.domain.user.domain.User;
import org.v1.domain.user.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserChecker {

    private final UserRepository userRepository;
    public boolean isUserEmailDuplicate(final User user) {
        return userRepository.checkByEmail(user);
    }
    public boolean isUserNicknameDuplicate(final User user) {
        return userRepository.checkByNickname(user);
    }
}
