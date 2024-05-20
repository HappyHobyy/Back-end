package org.v1.external;

import org.v1.model.article.GatheringArticle;
import org.v1.model.article.PhotoArticle;

import java.util.List;

public interface ExternalCommunitySender {
    void sendPopularPhotoArticle(List<PhotoArticle> popularCommunity, List<PhotoArticle> notPopularCommunity );
    void sendPopularGatheringArticle(List<GatheringArticle> popularCommunity, List<GatheringArticle> notPopularCommunity );

}
