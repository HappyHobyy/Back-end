package org.v1.rabbitmq;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.v1.model.Community;
import org.v1.model.CommunityStatusInfo;
import org.v1.model.PhotoArticle;
import org.v1.repository.ContentRepository;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class ExternalContentHandler implements ContentRepository {
    private final ExternalTextContentHandler textContentHandler;
    private final ExternalPhotoContentHandler photoContentHandler;
    @Override
    public Integer readTextContentCount(CommunityStatusInfo communityStatusInfo) {
        return null;
    }

    @Override
    public Integer readPhotoContentCount(CommunityStatusInfo communityStatusInfo) {
        return null;
    }

    @Override
    public List<PhotoArticle> readPopularPhotoContent(Community community) {
        return null;
    }
}
