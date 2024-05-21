package org.v1.model.content;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.v1.model.user.User;
import org.v1.model.community.Community;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GroupArticle {
    private final Long id;
    private final LocalDateTime date;
    private final User user;
    private final Integer likes;
    private final Integer comments;
    private final Integer countUsers;
    private final String firstImageUrl;
    private final Community community;

    public static GroupArticle withId(Long id, LocalDateTime date, User user, Integer likes, Integer comments, Integer countUsers, String firstImageUrl, Community community) {
        return new GroupArticle(id, date, user, likes, comments, countUsers, firstImageUrl, community);
    }
}
