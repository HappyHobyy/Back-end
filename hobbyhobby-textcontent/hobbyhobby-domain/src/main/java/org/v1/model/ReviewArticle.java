package org.v1.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewArticle {
    private final Long id;
    private final Article article;
    private final Content.Image image;
    private final Float stars;
}
