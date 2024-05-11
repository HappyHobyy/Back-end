package org.v1.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewArticle {
    private final long id;
    private final Article article;
    private final Content.Image image;
    private final double stars;

    public static ReviewArticle withoutId(Article article, Content.Image image, double stars) {
        return new ReviewArticle(0, article, image, stars);
    }

    public static ReviewArticle initialize(Article article) {
        return new ReviewArticle(0, article, null, 0.0);
    }
}
