package org.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementation.CashedContentReader;
import org.v1.implementation.CommunityReader;
import org.v1.implementation.ContentReader;
import org.v1.model.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommunityService {
    private final CommunityReader communityReader;
    private final ContentReader contentReader;
    private final CashedContentReader cashedContentReader;
    public List<Community> getPopularCommunity(){
        return communityReader.readPopularCommunities();
    }
    public List<UserCommunity> getUserCommunity(Long userId){
        return communityReader.readUserCommunities(userId);
    }
    public Contents getPopularContent(){
        Community populistCommunity = communityReader.readPopulistCommunity();

        Contents.PhotoArticles photoArticles = contentReader.readPopularPhotoContent(populistCommunity);
        Contents.GroupArticles groupArticles = contentReader.readPopularGroupContent(populistCommunity);
        return new Contents(photoArticles,groupArticles);
    }
}
