package org.v1.jparepository.gathering;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.v1.jpaentity.gathering.GatheringDetailJpaEntity;
import org.v1.jpaentity.gathering.UnionGatheringJpaEntity;
import org.v1.jpaentity.photo.PhotoArticleJpaEntity;

import java.time.LocalDateTime;

public interface UnionGatheringJpaRepository extends JpaRepository<UnionGatheringJpaEntity, Long> {
    @Query("SELECT uga FROM UnionGatheringJpaEntity uga JOIN FETCH uga.community1 Join FETCH uga.community2 WHERE uga.id = :id")
    UnionGatheringJpaEntity findWithCommunityById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE UnionGatheringJpaEntity uga SET uga.imageUrl = :newImageUrl WHERE uga.id = :id")
    void updateImagePath(@Param("id") Long id, @Param("newImageUrl") String newImageUrl);

    @Modifying
    @Transactional
    @Query("UPDATE UnionGatheringJpaEntity uga SET uga.joinedCount = uga.joinedCount + 1 WHERE uga.id = :id AND uga.joinedCount <= uga.communityMax")
    int updatePlusCount(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE UnionGatheringJpaEntity uga SET uga.joinedCount = uga.joinedCount - 1 WHERE uga.id = :id AND uga.joinedCount >= 0")
    void updateMinusCount(@Param("id") Long id);

}
