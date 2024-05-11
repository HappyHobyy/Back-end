package org.v1.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
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
