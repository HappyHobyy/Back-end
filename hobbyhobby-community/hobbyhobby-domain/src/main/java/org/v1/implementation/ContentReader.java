package org.v1.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.Community;
import org.v1.model.CommunityStatusInfo;
import org.v1.model.PhotoArticle;
import org.v1.repository.ContentRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class ContentReader {
    private final ContentRepository contentRepository;
    public Integer readTextContentCount(CommunityStatusInfo communityStatusInfo) {
        return contentRepository.readTextContentCount(communityStatusInfo);
    }
    public Integer readPhotoContentCount(CommunityStatusInfo communityStatusInfo) {
        return contentRepository.readPhotoContentCount(communityStatusInfo);
    }
    public List<PhotoArticle> readPopularPhotoContent(Community populistCommunity) {
        return contentRepository.readPopularPhotoContent(populistCommunity);
    }
}
