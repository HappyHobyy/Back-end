package org.v1.jparepository.gathering;

import org.springframework.data.jpa.repository.JpaRepository;
import org.v1.jpaentity.gathering.JoinedUnionGatheringJpaEntity;

public interface JoinedUnionGatheringJpaRepository extends JpaRepository<JoinedUnionGatheringJpaEntity,Long> {
    void deleteByUnionGathering_IdAndUser_Id(Long articleId,Long userId);
    boolean existsByUnionGathering_IdAndUser_Id(Long articleId,Long userId);
}
