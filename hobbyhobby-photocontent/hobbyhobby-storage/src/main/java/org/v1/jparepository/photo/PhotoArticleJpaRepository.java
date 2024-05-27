package org.v1.jparepository.photo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.v1.jpaentity.photo.PhotoArticleJpaEntity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

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

    @Modifying
    @Transactional
    @Query("UPDATE PhotoArticleJpaEntity pa SET pa.comments = pa.comments + 1 WHERE pa.id = :articleId")
    void incrementCommentsById(Long articleId);
    @Modifying
    @Transactional
    @Query("UPDATE PhotoArticleJpaEntity pa SET pa.comments = pa.comments - 1 WHERE pa.id = :articleId")
    void decrementCommentsById(Long articleId);

    @Query(value = "SELECT p FROM PhotoArticleJpaEntity p JOIN FETCH p.community ORDER BY p.createdAt DESC",
            countQuery = "SELECT COUNT(p) FROM PhotoArticleJpaEntity p")
    Page<PhotoArticleJpaEntity> findAllByOrderByCreatedAtDesc(Pageable pageable);
    @Query(value = "SELECT p FROM PhotoArticleJpaEntity p JOIN FETCH p.community ORDER BY p.likes DESC",
            countQuery = "SELECT COUNT(p) FROM PhotoArticleJpaEntity p")
    Page<PhotoArticleJpaEntity> findAllByOrderByLikesDesc(Pageable pageable);
    @Query("SELECT p FROM PhotoArticleJpaEntity p " +
            "WHERE p.community.id = :communityId " +
            "AND p.createdAt >= :startDate " +
            "ORDER BY p.likes DESC")
    Page<PhotoArticleJpaEntity> findAllByCommunityIdAndCreatedAtAfterOrderByLikesDesc(
            @Param("communityId") Long communityId,
            @Param("startDate") Instant startDate,
            Pageable pageable);

    @Query("SELECT p FROM PhotoArticleJpaEntity p " +
            "WHERE p.community.id != :communityId " +
            "AND p.createdAt >= :startDate " +
            "ORDER BY p.likes DESC")
    Page<PhotoArticleJpaEntity> findAllByCommunityIdNotAndCreatedAtAfterOrderByLikesDesc(
            @Param("communityId") Long communityId,
            @Param("startDate") Instant startDate,
            Pageable pageable);
}
