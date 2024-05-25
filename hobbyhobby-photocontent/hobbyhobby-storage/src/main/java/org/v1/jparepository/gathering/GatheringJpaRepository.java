package org.v1.jparepository.gathering;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.v1.jpaentity.gathering.GatheringJpaEntity;
import org.v1.jpaentity.photo.PhotoArticleJpaEntity;

public interface GatheringJpaRepository extends JpaRepository<GatheringJpaEntity,Long> {
    @Query("SELECT ga FROM GatheringJpaEntity ga JOIN FETCH ga.community WHERE ga.id = :id")
    GatheringJpaEntity findWithCommunityById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE GatheringJpaEntity ga SET ga.imageUrl = :newImageUrl WHERE ga.id = :id")
    void updateImagePath(@Param("id") Long id, @Param("newImageUrl") String newImageUrl);

    @Modifying
    @Transactional
    @Query("UPDATE GatheringJpaEntity ga SET ga.joinedCount = ga.joinedCount + 1 WHERE ga.id = :id AND ga.joinedCount <= ga.communityMax")
    int updatePlusCount(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE GatheringJpaEntity ga SET ga.joinedCount = ga.joinedCount - 1 WHERE ga.id = :id")
    void updateMinusCount(@Param("id") Long id);
}
