package org.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementation.CachedPhotoArticlesHandler;
import org.v1.implementation.CommunityReader;
import org.v1.implementation.ContentUpdater;
import org.v1.implementation.ContentReader;
import org.v1.model.*;

import java.util.List;

@Service
@AllArgsConstructor
public class CommunityService {
    private final CommunityReader communityReader;
    private final ContentReader contentReader;
    private final ContentUpdater contentUpdater;
    private final CachedPhotoArticlesHandler cachedPhotoArticlesHandler;
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
            cachedPhotoArticlesHandler.put("photoArticles",cachedPhotoArticles);
        }
        Contents.GroupArticles cachedGroupArticles = getCachedGroupArticles();
        if (cachedGroupArticles == null) {
            cachedGroupArticles= contentReader.readPopularGroupContent(populistCommunity);
            cachedPhotoArticlesHandler.put("groupArticles",cachedGroupArticles);
        }
        return new Contents(cachedPhotoArticles,cachedGroupArticles);
    }
    public void updatePhotoArticle(Contents.PhotoArticles photos){
        contentUpdater.updatePhotoArticle(photos);
    }
    public void clearCachedPhotoArticles(){
        cachedPhotoArticlesHandler.remove("photoArticles");
    }
    private Contents.PhotoArticles getCachedPhotoArticles() {
        return (Contents.PhotoArticles) cachedPhotoArticlesHandler.get("photoArticles");
    }
    private Contents.GroupArticles getCachedGroupArticles() {
        return (Contents.GroupArticles) cachedPhotoArticlesHandler.get("groupArticles");
    }
}
