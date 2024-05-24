package org.v1.jparepository.gathering;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.v1.jpaentity.gathering.GatheringDetailJpaEntity;
import org.v1.jpaentity.gathering.UnionGatheringDetailJpaEntity;

public interface UnionGatheringDetailJpaRepository extends JpaRepository<UnionGatheringDetailJpaEntity, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE UnionGatheringDetailJpaEntity ugd SET ugd.likes = ugd.likes + 1 WHERE ugd.unionGathering.id = :gatheringId")
    void incrementLikesById(Long gatheringId);
    @Modifying
    @Transactional
    @Query("UPDATE UnionGatheringDetailJpaEntity ugd SET ugd.likes = ugd.likes - 1 WHERE ugd.unionGathering.id = :gatheringId")
    void decrementLikesById(Long gatheringId);
}