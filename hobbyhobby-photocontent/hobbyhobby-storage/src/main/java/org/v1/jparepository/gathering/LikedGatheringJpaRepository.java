package org.v1.jparepository.gathering;

import org.springframework.data.jpa.repository.JpaRepository;
import org.v1.jpaentity.gathering.LikedGatheringJpaEntity;

public interface LikedGatheringJpaRepository extends JpaRepository<LikedGatheringJpaEntity,Long> {
    boolean existsLikedGatheringJpaEntityByGathering_Id(Long articleId);

    void deleteByGathering_IdAndUser_Id(Long articleId,Long userId);
}
