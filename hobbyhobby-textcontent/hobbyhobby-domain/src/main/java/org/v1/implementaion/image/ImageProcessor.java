package org.v1.implementaion.image;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.Content;
import org.v1.model.ReviewComment;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ImageProcessor {
    private final ImageRemover imageRemover;
    private final ImageAppender imageAppender;
    public List<Content.Image> appendImages(Long id, List<Content.Image> images) {
        return images.stream()
                .map(image -> {
                    String path = imageAppender.appendImage(image.data(), image.type() + id.toString());
                    return Content.Image.withoutData(image.index(), path,image.type());
                })
                .collect(Collectors.toList());
    }
    public void removeImages(List<Content.Image> images) {
        images.forEach(image -> imageRemover.removeImage(image.path()));
    }
    public void removeImage(Content.Image image) {
        imageRemover.removeImage(image.path());
    }
    public Content.Image appendImage(Long id,Content.Image image) {
        String path = imageAppender.appendImage(image.data(), image.type() + id.toString());
        return Content.Image.withoutData(image.index(), path, image.type());
    }
}
