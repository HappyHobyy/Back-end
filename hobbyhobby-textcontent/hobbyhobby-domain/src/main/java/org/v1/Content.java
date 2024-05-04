package org.v1;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Content {
    private final Long id;
    private final List<Text> text;
    private final List<Image> image;

    public record Image(Integer index, String path) {
    }
    public record Text(Integer index, String text) {
    }
}
