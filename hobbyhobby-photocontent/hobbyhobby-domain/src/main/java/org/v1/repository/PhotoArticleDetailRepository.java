package org.v1.repository;

import org.v1.model.photoartlcle.PhotoAriticleContent;
import org.v1.model.photoartlcle.PhotoArticleDetail;

public interface PhotoArticleDetailRepository {
    PhotoArticleDetail read(Long id);
    PhotoAriticleContent readContent(Long photoArticleId);
}
