package org.v1.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.v1.jpaentity.ArticleJpaEntity;

public interface ArticleJpaRepository extends JpaRepository<ArticleJpaEntity, Long> {

}
