package org.v1.rabbitmq;


import org.v1.model.community.Community;
import org.v1.model.content.PhotoArticle;
import org.v1.model.user.User;

import java.time.LocalDateTime;

public record PhotoArticleMessage(
        Long photoArticleId,
        LocalDateTime date,
        String nickname,
        Integer likes,
        Integer comments,
        String url,
        Long userId,
        String userName,
        String userImageUrl,
        Integer communityId

) {
    public PhotoArticle toArticle() {
        return PhotoArticle.withId(photoArticleId,date,new User(userId,userName,userImageUrl),likes,comments,url, Community.onlyWithId(communityId));
    }
}
