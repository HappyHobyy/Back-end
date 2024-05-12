package org.v1.repository;

import org.v1.model.community.Community;
import org.v1.model.content.Contents;

public interface ContentRepository {
    Contents.PhotoArticles readPopularPhotoContent(Community community);
    Contents.GroupArticles readPopularGroupContent(Community community);
    void updatePhotoArticle(Contents.PhotoArticles photos);
}
