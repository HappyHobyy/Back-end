package org.v1.jparepository.gathering;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.v1.jpaentity.gathering.GatheringDetailJpaEntity;

public interface GatheringDetailJpaRepository extends JpaRepository<GatheringDetailJpaEntity, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE GatheringDetailJpaEntity gd SET gd.likes = gd.likes + 1 WHERE gd.gathering.id = :gatheringId")
    void incrementLikesById(Long gatheringId);
    @Modifying
    @Transactional
    @Query("UPDATE GatheringDetailJpaEntity gd SET gd.likes = gd.likes - 1 WHERE gd.gathering.id = :gatheringId")
    void decrementLikesById(Long gatheringId);
}