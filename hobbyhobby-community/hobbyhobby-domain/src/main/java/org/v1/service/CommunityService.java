package org.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementation.community.CommunityUpdater;
import org.v1.implementation.content.CachedContentHandler;
import org.v1.implementation.community.CommunityReader;
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
    public List<Community> getPopularCommunity(){
        return communityReader.readPopularCommunities();
    }
    public List<Community> getUserCommunity(Long userId){
        return communityReader.readUserCommunities(userId);
    }
    public Contents getPopularContent(){
        Community populistCommunity = communityReader.readPopulistCommunity();
        Contents.PhotoArticles cachedPhotoArticles = getCachedPhotoArticles();
        if (cachedPhotoArticles == null) {
            cachedPhotoArticles= contentReader.readPopularPhotoContent(populistCommunity);
            if(cachedPhotoArticles != null) {
                cachedContentHandler.put("photoArticles", cachedPhotoArticles);
            }
        }
        Contents.GatheringArticles cachedGatheringArticles = getCachedGatheringArticles();
        if (cachedGatheringArticles == null) {
            cachedGatheringArticles = contentReader.readPopularGatheringContent(populistCommunity);
            cachedContentHandler.put("gatheringArticles", cachedGatheringArticles);
        }
        return new Contents(cachedPhotoArticles, cachedGatheringArticles);
    }
    public void updatePhotoArticle(Contents.PhotoArticles photos){
        contentUpdater.updatePhotoArticle(photos);
    }
    public void updateGatheringArticle(Contents.GatheringArticles photos){
        contentUpdater.updateGatheringArticle(photos);
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
