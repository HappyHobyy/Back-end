package org.v1.entityjparepository;


import org.springframework.stereotype.Repository;
import org.v1.jpaentity.UserJpaEntity;
import org.v1.jparepository.UserJpaRepository;
import org.v1.model.User;
import org.v1.repository.UserRepository;

import java.util.Optional;

@Repository
public class UserEntityJpaRepository implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    public UserEntityJpaRepository(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public Optional<User> appendUser(User user) {
        return userJpaRepository.save(UserJpaEntity.ofWithoutId(user)).toUser();
    }

    @Override
    public void update(User user) {
        userJpaRepository.save(UserJpaEntity.ofWithId(user));
    }

    @Override
    public Optional<User> readByTypeAndEmail(User user) {
        UserJpaEntity userJpaEntity = userJpaRepository.findUserJpaEntitiesByEmailAndType(user.getEmail(), user.getUserType());
        if (userJpaEntity == null) {
            return Optional.empty();
        }
        return userJpaEntity.toUser();
    }
    @Override
    public Optional<User> readById(final Long userId) {
        UserJpaEntity userJpaEntity = userJpaRepository.findUserById(userId);
        if (userJpaEntity == null) {
            return Optional.empty();
        }
        return userJpaEntity.toUser();
    }
    public Optional<User> readByEmail(final String userEmail) {
        UserJpaEntity userJpaEntity = userJpaRepository.findUserJpaEntitiesByEmail(userEmail);
        if (userJpaEntity == null) {
            return Optional.empty();
        }
        return userJpaEntity.toUser();
    }
    @Override
    public boolean checkByEmail(final User user) {
        return userJpaRepository.existsUserJpaEntitiesByEmail(user.getEmail());
    }
    @Override
    public boolean checkByNickname(final User user) {
        return userJpaRepository.existsUserJpaEntitiesByNickname(user.getNickname());
    }
    @Override
    public void remove(final Long userId) {
        userJpaRepository.deleteById(userId);
    }

}
