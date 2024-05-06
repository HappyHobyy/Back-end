package org.v1.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.v1.jpaentity.PhotoArticleJpaEntity;

public interface PhotoArticleJpaRepository extends JpaRepository<PhotoArticleJpaEntity, Long> {

}
