package org.v1.domain.user.repository;

import org.v1.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.v1.domain.user.domain.UserJpaEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserJpaEntity, Long> {
    boolean existsUserJpaEntitiesByEmail(final String email);
    boolean existsUserJpaEntitiesByNickname(final String nickName);

    Optional<UserJpaEntity> findUserJpaEntitiesByEmail(final String email);

    Optional<UserJpaEntity> findUserJpaEntitiesByEmailAndType(String email, User.UserType type);
}
