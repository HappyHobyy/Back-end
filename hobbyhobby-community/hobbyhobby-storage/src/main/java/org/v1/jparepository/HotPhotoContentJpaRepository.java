package org.v1.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.v1.jpaentity.CommunityUserJpaEntity;
import org.v1.jpaentity.HotPhotoContentJpaEntity;

import java.util.List;

public interface HotPhotoContentJpaRepository extends JpaRepository<HotPhotoContentJpaEntity, Long> {
    @Query("SELECT pa FROM HotPhotoContentJpaEntity pa JOIN FETCH pa.community JOIN FETCH pa.user WHERE pa.isPopulistCommunity = false")
    List<HotPhotoContentJpaEntity> findNotPopularWithCommunity();

    @Query("SELECT pa FROM HotPhotoContentJpaEntity pa JOIN FETCH pa.community JOIN FETCH pa.user WHERE pa.isPopulistCommunity = true")
    List<HotPhotoContentJpaEntity> findPopularWithCommunity();

}
