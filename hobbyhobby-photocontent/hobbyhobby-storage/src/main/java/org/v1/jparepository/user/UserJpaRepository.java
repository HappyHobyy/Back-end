package org.v1.jparepository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.v1.jpaentity.UserJpaEntity;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {
    @Modifying
    @Query("UPDATE UserJpaEntity u SET u.nickname = :nickname, u.imageUrl = :imageUrl WHERE u.id = :userId")
    void updateUser(@Param("userId") Long userId, @Param("nickname") String nickname, @Param("imageUrl") String imageUrl);
}
