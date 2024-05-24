package org.v1.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.v1.jpaentity.gathering.LikedGatheringJpaEntity;
import org.v1.jpaentity.gathering.LikedUnionGatheringJpaEntity;

public interface LikedUnionGatheringJpaRepository extends JpaRepository<LikedUnionGatheringJpaEntity,Long> {
    boolean existsLikedUnionGatheringJpaEntityByUnionGathering_Id(Long articleId);
}
