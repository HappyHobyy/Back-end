package org.v1.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Article {
    private final Long id;
    private final String title;
    private final LocalDateTime date;
    private final User user;
    private final Integer likes;
}
