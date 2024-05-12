package org.v1.repository;

import org.v1.model.Community;
import org.v1.model.Contents;
import org.v1.model.PhotoArticle;

import java.util.List;

public interface ContentRepository {
    Contents.PhotoArticles readPopularPhotoContent(Community community);
    Contents.GroupArticles readPopularGroupContent(Community community);
    void updatePhotoArticle(Contents.PhotoArticles photos);
}
