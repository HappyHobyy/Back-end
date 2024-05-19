package org.v1.dto;

import org.v1.model.content.PhotoArticle;

import java.time.LocalDateTime;

record PhotoArticleResponse(
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
    static PhotoArticleResponse fromPhotoArticle(PhotoArticle photoArticle) {
        return new PhotoArticleResponse(
                photoArticle.getId(),
                photoArticle.getDate(),
                photoArticle.getUser().id(),
                photoArticle.getUser().nickname(),
                photoArticle.getUser().path(),
                photoArticle.getLikes(),
                photoArticle.getComments(),
                photoArticle.getFirstImageUrl(),
                photoArticle.getCommunity().getId(),
                photoArticle.getCommunity().getCommunityName()
        );
    }
}