package org.v1.model.content;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.v1.model.user.User;
import org.v1.model.community.Community;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GatheringArticle {
    private final Long id;
    private final String title;
    private final LocalDateTime createdAt;
    private final User user;
    private final Integer joinedMax;
    private final Integer joinedCount;
    private final Integer likes;
    private final String imageUrl;
    private final List<Community> communities;

    public static GatheringArticle withId(Long id, String title, LocalDateTime date, User user, Integer joinedMax, Integer joinedCount, Integer likes, String imageUrl, List<Community> communityList) {
        return new GatheringArticle(id, title, date, user, joinedMax, joinedCount, likes, imageUrl, communityList);
    }
}
