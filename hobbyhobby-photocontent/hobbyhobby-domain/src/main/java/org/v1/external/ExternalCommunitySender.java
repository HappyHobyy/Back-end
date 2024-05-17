package org.v1.external;

import org.v1.model.photo.PhotoArticle;

import java.util.List;

public interface ExternalCommunitySender {
    void sendCommunityPopularArticle(List<PhotoArticle> popularCommunity,List<PhotoArticle> notPopularCommunity );
}
