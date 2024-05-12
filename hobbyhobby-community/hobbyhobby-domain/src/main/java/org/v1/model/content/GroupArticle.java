package org.v1.model.content;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.v1.model.user.User;
import org.v1.model.community.Community;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GroupArticle {
    private final Long id;
    private final User user;
    private final Integer likes;
    private final Integer comments;
    private final String firstImageUrl;
    private final Community community;
}
