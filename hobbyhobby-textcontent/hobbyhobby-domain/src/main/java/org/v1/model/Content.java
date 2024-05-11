package org.v1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@AllArgsConstructor
public class Content {
    private final List<Text> texts;
    private final List<Image> images;

    public record Image(Integer index, String path, File data,ImageType type) {
        public static Image withoutPath(Integer index, File data,ImageType type) {
            return new Image(index,null, data,type);
        }
        public static Image withoutData(Integer index, String path,ImageType type) {
            return new Image(index,path, null,type);
        }
        public Image updateIndex(Integer index) {
            return new Image(index, this.path(), this.data(),this.type());
        }
    }
    public enum ImageType {
        REVIEW,
        REVIEW_COMMENT,
        H_BOARD
    }

    public record Text(Integer index, String text) {
    }

    public Content calculateIndex() {
        AtomicInteger textCount = new AtomicInteger(0);
        AtomicInteger imageCount = new AtomicInteger(0);
        texts.forEach(text -> {
            if (text.index() != textCount.get()) {
                Image updatedImage = images.get(imageCount.get()).updateIndex(textCount.get());
                images.set(imageCount.getAndIncrement(), updatedImage);
            }
            textCount.incrementAndGet();
        });
        return new Content(texts, images);
    }
}
