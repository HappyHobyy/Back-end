package org.v1.jparepository.gathering;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.v1.jpaentity.gathering.UnionGatheringJpaEntity;

public interface UnionGatheringJpaRepository extends JpaRepository<UnionGatheringJpaEntity, Long> {
    @Query("SELECT uga FROM UnionGatheringJpaEntity uga JOIN FETCH uga.community1 Join FETCH uga.community2 WHERE uga.id = :id")
    UnionGatheringJpaEntity findWithCommunityById(@Param("id") Long id);
}
