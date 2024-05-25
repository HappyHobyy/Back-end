package org.v1.jparepository.gathering;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.v1.jpaentity.gathering.GatheringDetailJpaEntity;
import org.v1.jpaentity.gathering.UnionGatheringDetailJpaEntity;
import org.v1.jpaentity.gathering.UnionGatheringJpaEntity;

import java.time.LocalDateTime;

public interface GatheringDetailJpaRepository extends JpaRepository<GatheringDetailJpaEntity, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE GatheringDetailJpaEntity gd SET gd.likes = gd.likes + 1 WHERE gd.gathering.id = :gatheringId")
    void incrementLikesById(Long gatheringId);

    @Modifying
    @Transactional
    @Query("UPDATE GatheringDetailJpaEntity gd SET gd.likes = gd.likes - 1 WHERE gd.gathering.id = :gatheringId")
    void decrementLikesById(Long gatheringId);

    GatheringDetailJpaEntity getByGathering_Id(Long articleId);

    @Query("SELECT p FROM GatheringDetailJpaEntity p " +
            "WHERE p.gathering.community.id = :communityId " +
            "ORDER BY p.gathering.createdAt DESC")
    Page<GatheringDetailJpaEntity> findAllByCommunityIdAndCreatedAtAfterOrderByDesc(
            @Param("communityId") Long communityId,
            Pageable pageable);

    @Query("SELECT p FROM GatheringDetailJpaEntity p " +
            "ORDER BY p.gathering.createdAt DESC")
    Page<GatheringDetailJpaEntity> findAllByCommunityIdOrderByDesc(
            Pageable pageable);

}