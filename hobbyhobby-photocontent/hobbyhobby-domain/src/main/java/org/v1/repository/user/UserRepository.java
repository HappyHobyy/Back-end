package org.v1.repository.user;

import org.v1.model.user.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> readUser(Long userId);
    void appendUser(User user);
    void updateUser(User user);
    void removeUser(Long userId);
}
