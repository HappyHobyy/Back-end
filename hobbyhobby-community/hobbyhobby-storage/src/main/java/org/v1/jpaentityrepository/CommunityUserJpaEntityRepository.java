package org.v1.jpaentityrepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.v1.jparepository.CommunityUserJpaRepository;
import org.v1.model.user.User;
import org.v1.repository.UserRepository;
import org.v1.jpaentity.CommunityUserJpaEntity;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class CommunityUserJpaEntityRepository implements UserRepository {
    private final CommunityUserJpaRepository userJpaRepository;
    @Override
    public Optional<User> readUser(Long userId) {
        return userJpaRepository.findById(userId)
                .flatMap(CommunityUserJpaEntity::toUser);
    }
    @Override
    public void appendUser(User user) {
        userJpaRepository.save(CommunityUserJpaEntity.of(user));
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
