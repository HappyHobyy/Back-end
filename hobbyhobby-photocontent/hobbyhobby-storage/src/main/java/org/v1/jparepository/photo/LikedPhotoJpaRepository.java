package org.v1.jparepository.photo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.v1.jpaentity.photo.LikedPhotoJpaEntity;
import org.v1.jpaentity.photo.PhotoArticleJpaEntity;

public interface LikedPhotoJpaRepository extends JpaRepository<LikedPhotoJpaEntity, Long> {
    boolean existsLikedPhotoJpaEntityByPhotoContent_Id(Long articleId);
}
