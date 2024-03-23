package org.example.domain.user.repository;

import org.example.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByEmail(final String email);

    Optional<User> findByEmail(final String email);
}
