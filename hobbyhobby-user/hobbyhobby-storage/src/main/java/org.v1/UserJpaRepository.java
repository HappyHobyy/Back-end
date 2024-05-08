package org.v1;

import org.v1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {
    boolean existsUserJpaEntitiesByEmail(final String email);
    boolean existsUserJpaEntitiesByNickname(final String nickName);

    UserJpaEntity findUserJpaEntitiesByEmail(final String email);
    UserJpaEntity findUserById(Long userId);
    UserJpaEntity findUserJpaEntitiesByEmailAndType(String email, User.UserType type);
}
