package org.v1.rabbitmq;


import org.v1.model.Community;
import org.v1.model.PhotoArticle;
import org.v1.model.User;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
