package org.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementation.community.CommunityUpdater;
import org.v1.implementation.content.CachedContentHandler;
import org.v1.implementation.community.CommunityReader;
import org.v1.implementation.content.ContentEventPublisher;
import org.v1.implementation.content.ContentUpdater;
import org.v1.implementation.content.ContentReader;
import org.v1.model.community.Community;
import org.v1.model.content.Contents;
import org.v1.model.like.Like;

import java.util.List;

@Service
@AllArgsConstructor
public class CommunityService {
    private final CommunityReader communityReader;
    private final CommunityUpdater communityUpdater;
    private final ContentReader contentReader;
    private final ContentUpdater contentUpdater;
    private final CachedContentHandler cachedContentHandler;
    private final ContentEventPublisher contentEventPublisher;
    public List<Community> getPopularCommunity(){
        return communityReader.readPopularCommunities();
    }
    public List<Community> getUserCommunity(Long userId){
        return communityReader.readUserCommunities(userId);
    }
    public List<Community> getRecommendCommunity(Long userId){
        return communityReader.readRecommendCommunities(userId);
    }
    public Contents getPopularContent(){
        Contents.PhotoArticles cachedPhotoArticles = getCachedPhotoArticles();
        if (cachedPhotoArticles == null) {
            cachedPhotoArticles= contentReader.readPopularPhotoContent();
            if(cachedPhotoArticles != null) {
                cachedContentHandler.put("photoArticles", cachedPhotoArticles);
            }
        }
        Contents.GatheringArticles cachedGatheringArticles = getCachedGatheringArticles();
        if (cachedGatheringArticles == null) {
            cachedGatheringArticles = contentReader.readPopularGatheringContent();
            cachedContentHandler.put("gatheringArticles", cachedGatheringArticles);
        }
        return new Contents(cachedPhotoArticles, cachedGatheringArticles);
    }
    public void updatePhotoArticle(Contents.PhotoArticles photos){
        contentUpdater.updatePhotoArticle(photos);
        contentEventPublisher.publishPhotoArticleResetEvent();
    }
    public void updateGatheringArticle(Contents.GatheringArticles photos){
        contentUpdater.updateGatheringArticle(photos);
        contentEventPublisher.publishGatheringResetEvent();
    }
    public void clearCachedPhotoArticles(){
        cachedContentHandler.remove("photoArticles");
    }
    public void clearCachedGatheringArticles(){
        cachedContentHandler.remove("gatheringArticles");
    }
    public void likeCommunity(Like like){
        communityUpdater.updateCommunityLike(like);
    }
    private Contents.PhotoArticles getCachedPhotoArticles() {
        return (Contents.PhotoArticles) cachedContentHandler.get("photoArticles");
    }
    private Contents.GatheringArticles getCachedGatheringArticles() {
        return (Contents.GatheringArticles) cachedContentHandler.get("gatheringArticles");
    }
}
