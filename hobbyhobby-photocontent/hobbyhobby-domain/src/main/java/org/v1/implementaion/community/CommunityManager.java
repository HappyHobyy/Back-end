package org.v1.implementaion.community;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.external.ExternalCommunitySender;
import org.v1.model.article.ArticleType;
import org.v1.model.article.GatheringArticle;
import org.v1.model.article.PhotoArticle;
import org.v1.repository.community.CommunityRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class CommunityManager {
    private final CommunityRepository repository;
    private final ExternalCommunitySender communitySender;
    public void plusCommunityLikes(final Integer targetCommunityId, final ArticleType articleType) {
        repository.plusCommunityLikes(targetCommunityId,articleType);
    }
    public void minusCommunityLikes(final Integer targetCommunityId,final ArticleType articleType) {
        repository.minusCommunityLikes(targetCommunityId,articleType);
    }
    public void resetCommunityLikes() {
        repository.resetCommunityLikes();
    }
    public int readPopulistCommunity(){
        return repository.readPopulistCommunity();
    }
    public void sendPopularPhotoArticle(List<PhotoArticle> popularCommunity, List<PhotoArticle> notPopularCommunity){
        communitySender.sendPopularPhotoArticle(popularCommunity,notPopularCommunity);
    }
    public void sendPopularGatheringArticle(List<GatheringArticle> popularCommunity, List<GatheringArticle> notPopularCommunity){
        communitySender.sendPopularGatheringArticle(popularCommunity,notPopularCommunity);
    }
}
