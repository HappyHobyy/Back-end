package org.v1.domain.user.implementation;

import lombok.RequiredArgsConstructor;
import org.v1.domain.user.domain.User;
import org.v1.domain.user.domain.UserJpaEntity;
import org.v1.domain.user.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAppender {
    private final UserRepository userRepository;
    public User appendUser(
            final User user
    ){
        return userRepository.save(UserJpaEntity.ofWithoutId(user)).toUser();
    }
    public User updateUser(
            final User user
    ){
        return userRepository.save(UserJpaEntity.ofWithId(user)).toUser();
    }
}
