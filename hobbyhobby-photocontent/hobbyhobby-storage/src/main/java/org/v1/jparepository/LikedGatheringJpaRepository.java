package org.v1.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.v1.jpaentity.gathering.LikedGatheringJpaEntity;

public interface LikedGatheringJpaRepository extends JpaRepository<LikedGatheringJpaEntity,Long> {
    boolean existsLikedGatheringJpaEntityByGathering_Id(Long articleId);
}
