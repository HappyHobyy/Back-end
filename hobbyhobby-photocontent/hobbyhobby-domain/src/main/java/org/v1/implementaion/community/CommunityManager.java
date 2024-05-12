package org.v1.implementaion.community;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.external.ExternalCommunitySender;
import org.v1.model.Like;
import org.v1.model.PhotoArticle;
import org.v1.repository.CommunityRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class CommunityManager {
    private final CommunityRepository communityRepository;
    private final ExternalCommunitySender externalCommunitySender;
    public void plusCommunityLikes(final Integer targetCommunityId) {
        communityRepository.plusCommunityLikes(targetCommunityId);
    }
    public void minusCommunityLikes(final Integer targetCommunityId) {
        communityRepository.minusCommunityLikes(targetCommunityId);
    }
    public void resetCommunityLikes() {
        communityRepository.resetCommunityLikes();
    }
    public Integer readPopulistCommunity(){
        return communityRepository.readPopulistCommunity();
    }
    public void sendPopularCommunityArticle(List<PhotoArticle> articles){
        externalCommunitySender.sendPopularCommunityArticle(articles);
    }
    public void sendNotPopularCommunityArticle(List<PhotoArticle> articles){
        externalCommunitySender.sendNotPopularCommunityArticle(articles);
    }
}