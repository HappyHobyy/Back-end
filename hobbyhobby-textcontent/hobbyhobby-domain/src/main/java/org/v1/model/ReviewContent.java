package org.v1.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewContent{
    private final Content.Text text;
    private final Content.Image image;
    public static ReviewContent content(Content.Text text, Content.Image image) {
        return new ReviewContent(text, image);
    }
    public Optional<Content.Text> getText() {
        return Optional.ofNullable(text);
    }

    public Optional<Content.Image> getImage() {
        return Optional.ofNullable(image);
    }
}
