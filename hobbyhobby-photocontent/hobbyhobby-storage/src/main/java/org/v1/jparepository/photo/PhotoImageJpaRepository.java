package org.v1.jparepository.photo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.v1.jpaentity.photo.PhotoImageJpaEntity;

import java.util.List;

public interface PhotoImageJpaRepository extends JpaRepository<PhotoImageJpaEntity, Long> {
    List<PhotoImageJpaEntity> findAllByPhotoContent_Id(Long photoContentId);
}
