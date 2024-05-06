package org.v1.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.v1.jpaentity.ArticleDetailJpaEntity;
import org.v1.jpaentity.ArticleJpaEntity;

public interface ArticleDetailJpaRepository extends JpaRepository<ArticleDetailJpaEntity, Long> {

}
