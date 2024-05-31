package org.v1.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.v1.jpaentity.CommunityUserJpaEntity;
import org.v1.jpaentity.HotPhotoContentJpaEntity;
import org.v1.jpaentity.HotUnionGatheringJpaEntity;

import java.util.List;

public interface HotUnionGatheringJpaRepository extends JpaRepository<HotUnionGatheringJpaEntity, Long> {
    @Query("SELECT ga FROM HotUnionGatheringJpaEntity ga JOIN FETCH ga.community1 JOIN FETCH ga.community2 JOIN FETCH ga.user WHERE ga.isPopulistCommunity = false")
    List<HotUnionGatheringJpaEntity> findNotPopularWithCommunity();

    @Query("SELECT ga FROM HotUnionGatheringJpaEntity ga JOIN FETCH ga.community1 JOIN FETCH ga.community2 JOIN FETCH ga.user WHERE ga.isPopulistCommunity = true")
    List<HotUnionGatheringJpaEntity> findPopularWithCommunity();
}
