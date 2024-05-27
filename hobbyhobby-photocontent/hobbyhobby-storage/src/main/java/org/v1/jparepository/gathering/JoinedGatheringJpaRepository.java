package org.v1.jparepository.gathering;

import org.springframework.data.jpa.repository.JpaRepository;
import org.v1.jpaentity.gathering.JoinedGatheringJpaEntity;
import org.v1.jpaentityrepository.gathering.GatheringMemberJpaEntityRepository;

public interface JoinedGatheringJpaRepository extends JpaRepository<JoinedGatheringJpaEntity,Long> {
    void deleteByGathering_IdAndUser_Id(Long articleId,Long userId);

    boolean existsByGathering_IdAndUser_Id(Long articleId,Long userId);


}
