package org.v1.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewComment {
    private final Long id;
    private final Comment comment;
    private Float stars;
    private final Content.Image image;
    public static ReviewComment withoutId(Comment comment,Float stars, Content.Image image) {
        return new ReviewComment(null,comment,stars, image);
    }
}
