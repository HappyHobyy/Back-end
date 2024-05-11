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
    private double stars;
    private final Content content;
    public static ReviewComment withoutId(Comment comment,double stars, Content content) {
        return new ReviewComment(null,comment,stars, content);
    }
    public static ReviewComment withId(Long id,Comment comment,double stars, Content content) {
        return new ReviewComment(id,comment,stars, content);
    }
    public ReviewComment changeComment(Comment comment) {
        return new ReviewComment(id,comment,stars, content);
    }
}
