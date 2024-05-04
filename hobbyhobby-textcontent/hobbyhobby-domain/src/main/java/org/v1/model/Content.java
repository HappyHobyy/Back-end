package org.v1.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
@Getter
@AllArgsConstructor
public class Content {
    private final List<Text> texts;
    private final List<Image> images;
    public record Image(Integer index, String path,byte[] data) {
        public static Image withoutPath(Integer index, byte[] data) {
            return new Image(index,null,data);
        }
    }
    public record Text(Integer index, String text) {
    }
}
