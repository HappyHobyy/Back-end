package org.v1.domain.user.implementation;

import lombok.RequiredArgsConstructor;
import org.v1.domain.user.domain.User;
import org.v1.domain.user.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserRemover {

    private final UserRepository userRepository;

    public void remove(final Long userId) {
        userRepository.deleteById(userId);
    }
}
