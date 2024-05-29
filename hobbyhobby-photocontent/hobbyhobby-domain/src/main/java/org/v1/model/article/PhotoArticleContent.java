package org.v1.model.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.v1.model.imageVideo.ImageVideo;

import java.util.List;

@Getter
@AllArgsConstructor
public class PhotoArticleContent {
    private final String text;
    private final List<ImageVideo> images;

    public PhotoArticleContent updateImages(List<ImageVideo> images) {
        return new PhotoArticleContent(text, images);
    }
}
