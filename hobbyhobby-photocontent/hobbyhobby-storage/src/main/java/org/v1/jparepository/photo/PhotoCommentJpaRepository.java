package org.v1.jparepository.photo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.v1.jpaentity.photo.PhotoCommentJpaEntity;
import org.v1.jpaentityrepository.photo.PhotoCommentJpaEntityRepository;

import java.util.List;

public interface PhotoCommentJpaRepository extends JpaRepository<PhotoCommentJpaEntity, Long> {
    @Query("SELECT pc FROM PhotoCommentJpaEntity pc JOIN FETCH pc.photoContent JOIN FETCH pc.user WHERE pc.photoContent.id = :articleId")
    List<PhotoCommentJpaEntity> findByPhotoContent_Id(@Param("articleId") Long articleId);
}
