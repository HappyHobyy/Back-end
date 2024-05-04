package org.v1.domain.user.repository;

import org.v1.domain.user.domain.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    void update(User user);

    Optional<User> readByTypeAndEmail(User user);

    Optional<User> readById(Long userId);

    Optional<User> readByEmail(String userEmail);

    boolean checkByEmail(User user);

    boolean checkByNickname(User user);

    void remove(Long userId);
}