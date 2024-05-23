package org.v1.repository;

import org.v1.model.community.Community;
import org.v1.model.content.Contents;

public interface ContentRepository {
    Contents.PhotoArticles readPopularPhotoContent(Community community);
    Contents.GatheringArticles readPopularGatheringContent(Community community);
    void updatePhotoArticle(Contents.PhotoArticles photos);
    void updateGatheringArticle(Contents.GatheringArticles photos);
}
