package org.v1.jparepository.photo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.v1.jpaentity.photo.PhotoArticleJpaEntity;

public interface PhotoArticleJpaRepository extends JpaRepository<PhotoArticleJpaEntity, Long> {
    @Query("SELECT pa FROM PhotoArticleJpaEntity pa JOIN FETCH pa.community WHERE pa.id = :id")
    PhotoArticleJpaEntity findWithCommunityById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE PhotoArticleJpaEntity pa SET pa.likes = pa.likes + 1 WHERE pa.id = :articleId")
    void incrementLikesById(Long articleId);
    @Modifying
    @Transactional
    @Query("UPDATE PhotoArticleJpaEntity pa SET pa.likes = pa.likes - 1 WHERE pa.id = :articleId")
    void decrementLikesById(Long articleId);
}
