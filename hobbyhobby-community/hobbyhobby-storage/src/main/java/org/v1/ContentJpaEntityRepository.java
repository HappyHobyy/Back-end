package org.v1;

import org.springframework.stereotype.Repository;
import org.v1.model.community.Community;
import org.v1.model.content.Contents;
import org.v1.repository.ContentRepository;

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
