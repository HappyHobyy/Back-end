package org.v1.model;

import java.util.Optional;

public record ReviewContent(Content.Text text, Content.Image image) {

    public Optional<Content.Text> getText() {
        return Optional.ofNullable(text);
    }

    public Optional<Content.Image> getImage() {
        return Optional.ofNullable(image);
    }
}
