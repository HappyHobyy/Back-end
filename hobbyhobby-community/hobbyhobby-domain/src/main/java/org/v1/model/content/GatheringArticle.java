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
    private final LocalDateTime date;
    private final User user;
    private final Integer likes;
    private final Integer countUsers;
    private final String firstImageUrl;
    private final List<Community> communities;

    public static GatheringArticle withId(Long id, LocalDateTime date, User user, Integer likes, Integer countUsers, String firstImageUrl, List<Community> communityList) {
        return new GatheringArticle(id, date, user, likes, countUsers, firstImageUrl, communityList);
    }
}
