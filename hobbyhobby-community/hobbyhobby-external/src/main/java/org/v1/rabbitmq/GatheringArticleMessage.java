package org.v1.rabbitmq;

import org.v1.model.community.Community;
import org.v1.model.content.GatheringArticle;
import org.v1.model.content.PhotoArticle;
import org.v1.model.user.User;

import java.time.LocalDateTime;
import java.util.List;

public record GatheringArticleMessage (
        Long gatheringArticleId,
        LocalDateTime date,
        String title,
        String nickname,
        Integer joinedMax,
        Integer joinedCount,
        Integer likes,
        String url,
        Long userId,
        List<Integer> communityIds
){
    public GatheringArticle toArticle() {
        return GatheringArticle.withId(gatheringArticleId,title, date, new User(userId, nickname, null), likes, joinedMax,joinedCount, url,
                communityIds.stream()
                .map(Community::onlyWithId
                ).toList());
    }

}
