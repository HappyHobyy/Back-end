package org.v1.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.v1.jpaentity.CommunityJpaEntity;
import org.v1.jpaentity.LikedCommunityJpaEntity;

public interface CommunityJpaRepository extends JpaRepository<CommunityJpaEntity, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE CommunityJpaEntity c SET c.totalLikes = c.totalLikes + 1 WHERE c.id = :id")
    void incrementLikesById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE CommunityJpaEntity c SET c.totalLikes = c.totalLikes - 1 WHERE c.id = :id")
    void decrementLikesById(Long id);
}
