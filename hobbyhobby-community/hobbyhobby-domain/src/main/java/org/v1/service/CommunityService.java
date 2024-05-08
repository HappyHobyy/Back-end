package org.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
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
    public List<CommunityStatusInfo> getPopularCommunity(Long userId){
        List<CommunityStatusInfo> userCommunityList = communityReader.readPopularCommunities(userId);
        return userCommunityList.stream()
                .map(this::calculateCommunityStatus)
                .collect(Collectors.toList());
    }
    public List<CommunityStatusInfo> getUserCommunity(Long userId){
        List<CommunityStatusInfo> userCommunityList = communityReader.readUserCommunities(userId);
        return userCommunityList.stream()
                .map(this::calculateCommunityStatus)
                .collect(Collectors.toList());
    }
    public Contents getPopularContent(){
        Community populistCommunity = communityReader.readPopulistCommunity();
        List<PhotoArticle> photoArticles = contentReader.readPopularPhotoContent(populistCommunity);
        return new Contents(photoArticles);
    }
    private CommunityStatusInfo calculateCommunityStatus(CommunityStatusInfo communityStatusInfo) {
        Integer textContentCount = contentReader.readTextContentCount(communityStatusInfo);
        Integer photoContentCount = contentReader.readPhotoContentCount(communityStatusInfo);
        Integer totalContentCount = textContentCount + photoContentCount;
        UserStatus userStatus = UserStatus.onlyUserHistoryCount(totalContentCount);
        return new CommunityStatusInfo(communityStatusInfo.getCommunity(), userStatus);
    }
}
