package org.v1.jparepository.community;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.v1.jpaentity.community.CommunityJpaEntity;

public interface CommunityJpaRepository extends JpaRepository<CommunityJpaEntity, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE CommunityJpaEntity c SET c.communityLikeCount = c.communityLikeCount + 1 WHERE c.id = :id")
    void increaseLikeCount(@Param("id") Long id);
    @Modifying
    @Transactional
    @Query("UPDATE CommunityJpaEntity c SET c.communityLikeCount = c.communityLikeCount - 1 WHERE c.id = :id")
    void decreaseLikeCount(@Param("id") Long id);
    @Modifying
    @Transactional
    @Query("UPDATE CommunityJpaEntity c SET c.communityLikeCount = 0")
    void resetAllLikeCount();

    CommunityJpaEntity findTopByOrderByCommunityLikeCountDesc();
}
