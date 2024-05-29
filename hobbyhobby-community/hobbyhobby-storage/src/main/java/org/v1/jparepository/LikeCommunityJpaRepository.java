package org.v1.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.v1.jpaentity.LikedCommunityJpaEntity;

import java.util.List;

public interface LikeCommunityJpaRepository extends JpaRepository<LikedCommunityJpaEntity, Long> {
    void deleteByUser_IdAndCommunity_Id(Long userId, Long communityId);
    List<LikedCommunityJpaEntity> findAllByUser_Id(Long userId);
}
