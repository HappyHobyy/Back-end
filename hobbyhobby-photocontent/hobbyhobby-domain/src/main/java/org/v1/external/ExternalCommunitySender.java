package org.v1.external;

import org.v1.model.PhotoArticle;

import java.util.List;

public interface ExternalCommunitySender {
    void sendPopularCommunityArticle(List<PhotoArticle> photos);
    void sendNotPopularCommunityArticle(List<PhotoArticle> photos);
}
