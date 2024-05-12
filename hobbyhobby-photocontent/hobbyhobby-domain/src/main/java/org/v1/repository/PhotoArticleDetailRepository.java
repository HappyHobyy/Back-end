package org.v1.repository;

import org.v1.model.*;

import java.util.List;

public interface PhotoArticleDetailRepository {
    PhotoArticleDetail read(Long id);
    Content readContent(Long photoArticleId);
}
