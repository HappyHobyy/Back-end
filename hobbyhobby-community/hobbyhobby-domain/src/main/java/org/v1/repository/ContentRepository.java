package org.v1.repository;

import org.v1.model.Community;
import org.v1.model.CommunityStatusInfo;
import org.v1.model.PhotoArticle;

import java.time.LocalDateTime;
import java.util.List;

public interface ContentRepository {
    Integer readTextContentCount(CommunityStatusInfo communityStatusInfo);
    Integer readPhotoContentCount(CommunityStatusInfo communityStatusInfo);
    List<PhotoArticle> readPopularPhotoContent(Community community);
}
