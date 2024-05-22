package org.v1.model.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.v1.model.imageVideo.ImageVideo;

import java.io.File;
import java.util.List;

@Getter
@AllArgsConstructor
public class PhotoAriticleContent {
    private final String text;
    private final List<ImageVideo> images;
}
