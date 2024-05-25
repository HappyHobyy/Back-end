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

import java.time.LocalDateTime;

public interface UnionGatheringDetailJpaRepository extends JpaRepository<UnionGatheringDetailJpaEntity, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE UnionGatheringDetailJpaEntity ugd SET ugd.likes = ugd.likes + 1 WHERE ugd.unionGathering.id = :gatheringId")
    void incrementLikesById(Long gatheringId);

    @Modifying
    @Transactional
    @Query("UPDATE UnionGatheringDetailJpaEntity ugd SET ugd.likes = ugd.likes - 1 WHERE ugd.unionGathering.id = :gatheringId")
    void decrementLikesById(Long gatheringId);

    UnionGatheringDetailJpaEntity getByUnionGathering_Id(Long articleId);

    @Query("SELECT p FROM UnionGatheringDetailJpaEntity p " +
            "WHERE (p.unionGathering.community1.id = :communityId OR p.unionGathering.community2.id = :communityId) " +
            "AND p.unionGathering.createdAt >= :startDate " +
            "ORDER BY p.likes DESC")
    Page<UnionGatheringDetailJpaEntity> findAllByCommunityIdAndCreatedAtAfterOrderByLikesDesc(
            @Param("communityId") Long communityId,
            @Param("startDate") LocalDateTime startDate,
            Pageable pageable);

    @Query("SELECT p FROM UnionGatheringDetailJpaEntity p " +
            "WHERE NOT (p.unionGathering.community1.id = :communityId OR p.unionGathering.community2.id = :communityId) " +
            "AND p.unionGathering.createdAt >= :startDate " +
            "ORDER BY p.likes DESC")
    Page<UnionGatheringDetailJpaEntity> findAllByCommunityIdNotAndCreatedAtAfterOrderByLikesDesc(
            @Param("communityId") Long communityId,
            @Param("startDate") LocalDateTime startDate,
            Pageable pageable);

    @Query("SELECT p FROM UnionGatheringDetailJpaEntity p " +
            "WHERE (p.unionGathering.community2.id = :communityId2 AND p.unionGathering.community1.id = :communityId1) " +
            "   OR (p.unionGathering.community2.id = :communityId1 AND p.unionGathering.community1.id = :communityId2) " +
            "ORDER BY p.unionGathering.createdAt DESC")
    Page<UnionGatheringDetailJpaEntity> findAllByCommunityIdAndCreatedAtAfterOrderByDesc(
            @Param("communityId1") Long communityId1,
            @Param("communityId2") Long communityId2,
            Pageable pageable);

    @Query("SELECT p FROM UnionGatheringDetailJpaEntity p " +
            "ORDER BY p.unionGathering.createdAt DESC")
    Page<UnionGatheringDetailJpaEntity> findAllByCommunityIdOrderByDesc(
            Pageable pageable);
}