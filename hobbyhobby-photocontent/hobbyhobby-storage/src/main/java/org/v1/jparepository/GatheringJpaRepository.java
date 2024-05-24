package org.v1.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.v1.jpaentity.gathering.GatheringJpaEntity;
import org.v1.jpaentity.photo.PhotoArticleJpaEntity;

public interface GatheringJpaRepository extends JpaRepository<GatheringJpaEntity,Long> {
    @Query("SELECT ga FROM GatheringJpaEntity ga JOIN FETCH ga.community WHERE ga.id = :id")
    PhotoArticleJpaEntity findWithCommunityById(@Param("id") Long id);
}
