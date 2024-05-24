package org.v1.jparepository.photo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.v1.jpaentity.photo.PhotoCommentJpaEntity;
import org.v1.jpaentityrepository.photo.PhotoCommentJpaEntityRepository;

import java.util.List;

public interface PhotoCommentJpaRepository extends JpaRepository<PhotoCommentJpaEntity, Long> {
    List<PhotoCommentJpaEntity> readPhotoCommentJpaEntitiesByPhotoContent_Id(Long articleId);
}
