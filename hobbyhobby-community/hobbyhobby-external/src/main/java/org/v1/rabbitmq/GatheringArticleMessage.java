package org.v1.rabbitmq;

import org.v1.model.community.Community;
import org.v1.model.content.GatheringArticle;
import org.v1.model.content.PhotoArticle;
import org.v1.model.user.User;

import java.time.LocalDateTime;
import java.util.List;

public record GatheringArticleMessage(
        Long gatheringArticleId,
        LocalDateTime date,
        String nickname,
        Integer likes,
        Integer userCounts,
        Long userId,
        String userName,
        String userImageUrl,
        List<Integer> communityIds
) {
    public GatheringArticle toArticle() {
        return GatheringArticle.withId(gatheringArticleId, date, new User(userId, userName, userImageUrl), likes, userCounts, userImageUrl,
                communityIds.stream()
                .map(Community::onlyWithId
                ).toList());
    }

}
