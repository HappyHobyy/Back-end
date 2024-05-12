package org.v1.model.content;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.v1.model.user.User;
import org.v1.model.community.Community;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PhotoArticle {
    private final Long id;
    private final LocalDateTime date;
    private final User user;
    private final Integer likes;
    private final Integer comments;
    private final String firstImageUrl;
    private final Community community;
    public static PhotoArticle withId(Long id,LocalDateTime date, User user, Integer likes, Integer comments, String firstImageUrl, Community community) {
        return new PhotoArticle(id, date,user, likes, comments, firstImageUrl, community);
    }
}
