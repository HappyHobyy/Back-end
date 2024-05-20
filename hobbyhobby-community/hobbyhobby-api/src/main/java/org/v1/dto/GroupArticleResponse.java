package org.v1.dto;

import org.v1.model.content.GroupArticle;

import java.time.LocalDateTime;

record GroupArticleResponse(
        Long id,
        LocalDateTime date,
        Long userId,
        String userNickName,
        String userImagePath,
        Integer likes,
        Integer comments,
        String firstImageUrl,
        Integer communityId,
        String communityName
) {
    static GroupArticleResponse fromGroupArticle(GroupArticle groupArticle) {
        return new GroupArticleResponse(
                groupArticle.getId(),
                groupArticle.getDate(),
                groupArticle.getUser().id(),
                groupArticle.getUser().nickname(),
                groupArticle.getUser().path(),
                groupArticle.getLikes(),
                groupArticle.getComments(),
                groupArticle.getFirstImageUrl(),
                groupArticle.getCommunity().getId(),
                groupArticle.getCommunity().getCommunityName()
        );
    }
}
