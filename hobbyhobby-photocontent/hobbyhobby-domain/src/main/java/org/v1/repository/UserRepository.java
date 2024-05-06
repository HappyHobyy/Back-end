package org.v1.repository;

import org.v1.model.User;

public interface UserRepository {
    User readUser(Long userId);
}
