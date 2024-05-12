package org.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementation.content.CachedContentHandler;
import org.v1.implementation.community.CommunityReader;
import org.v1.implementation.content.ContentUpdater;
import org.v1.implementation.content.ContentReader;
import org.v1.model.community.Community;
import org.v1.model.community.UserCommunity;
import org.v1.model.content.Contents;

import java.util.List;

@Service
@AllArgsConstructor
public class CommunityService {
    private final CommunityReader communityReader;
    private final ContentReader contentReader;
    private final ContentUpdater contentUpdater;
    private final CachedContentHandler cachedContentHandler;
    public List<Community> getPopularCommunity(){
        return communityReader.readPopularCommunities();
    }
    public List<UserCommunity> getUserCommunity(Long userId){
        return communityReader.readUserCommunities(userId);
    }
    public Contents getPopularContent(){
        Community populistCommunity = communityReader.readPopulistCommunity();
        Contents.PhotoArticles cachedPhotoArticles = getCachedPhotoArticles();
        if (cachedPhotoArticles == null) {
            cachedPhotoArticles= contentReader.readPopularPhotoContent(populistCommunity);
            cachedContentHandler.put("photoArticles",cachedPhotoArticles);
        }
        Contents.GroupArticles cachedGroupArticles = getCachedGroupArticles();
        if (cachedGroupArticles == null) {
            cachedGroupArticles= contentReader.readPopularGroupContent(populistCommunity);
            cachedContentHandler.put("groupArticles",cachedGroupArticles);
        }
        return new Contents(cachedPhotoArticles,cachedGroupArticles);
    }
    public void updatePhotoArticle(Contents.PhotoArticles photos){
        contentUpdater.updatePhotoArticle(photos);
    }
    public void clearCachedPhotoArticles(){
        cachedContentHandler.remove("photoArticles");
    }
    private Contents.PhotoArticles getCachedPhotoArticles() {
        return (Contents.PhotoArticles) cachedContentHandler.get("photoArticles");
    }
    private Contents.GroupArticles getCachedGroupArticles() {
        return (Contents.GroupArticles) cachedContentHandler.get("groupArticles");
    }
}
