package org.v1.repository.photo;

import org.v1.model.photo.PhotoAriticleContent;
import org.v1.model.photo.PhotoArticleDetail;

public interface PhotoArticleDetailRepository {
    PhotoArticleDetail read(Long id);
    PhotoAriticleContent readContent(Long photoArticleId);
}
