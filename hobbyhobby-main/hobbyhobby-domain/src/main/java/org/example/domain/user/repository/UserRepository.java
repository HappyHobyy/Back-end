package org.example.domain.user.repository;

import org.example.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByEmailAndType(final String email, final String type);

    User findByEmail(final String email);

    Optional<User> findUserByEmailAndType(final String email, final String type);
}
