package org.v1;

import org.springframework.stereotype.Repository;
import org.v1.model.Community;
import org.v1.model.Contents;
import org.v1.model.PhotoArticle;
import org.v1.repository.ContentRepository;

import java.util.List;

@Repository
public class ContentJpaEntityRepository implements ContentRepository {
    @Override
    public Contents.PhotoArticles readPopularPhotoContent(Community community) {
        return null;
    }

    @Override
    public Contents.GroupArticles readPopularGroupContent(Community community) {
        return null;
    }

    @Override
    public void updatePhotoArticle(Contents.PhotoArticles photos) {
    }

}
