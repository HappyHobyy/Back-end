package org.v1.jpaentityrepository.user;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.v1.jpaentity.user.UserJpaEntity;
import org.v1.jparepository.user.UserJpaRepository;
import org.v1.model.user.User;
import org.v1.repository.user.UserRepository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserJpaEntityRepository implements UserRepository {
    private final UserJpaRepository userJpaRepository;
    @Override
    public Optional<User> readUser(Long userId) {
        return userJpaRepository.findById(userId)
                .flatMap(UserJpaEntity::toUser);
    }
    @Override
    public void appendUser(User user) {
        userJpaRepository.save(UserJpaEntity.of(user));
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userJpaRepository.updateUser(user.id(),user.nickname(),user.imageUrl());
    }

    @Override
    public void removeUser(Long userId) {
        userJpaRepository.deleteById(userId);
    }
}
