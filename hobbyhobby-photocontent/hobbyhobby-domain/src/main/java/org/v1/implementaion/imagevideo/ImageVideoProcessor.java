package org.v1.implementaion.imagevideo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.Content;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ImageVideoProcessor {
    private final ImageVideoRemover imageVideoRemover;
    private final ImageVideoAppender imageVideoAppender;
    public List<Content.ImageVideo> appendFiles(Long id, List<Content.ImageVideo> files) {
        return files.stream()
                .map(file -> {
                    String path = imageVideoAppender.appendFile(file.data(), file.fileType() + id.toString());
                    return Content.ImageVideo.withoutData(file.index(), path,file.fileType());
                })
                .collect(Collectors.toList());
    }
    public void removeImages(List<Content.ImageVideo> images) {
        images.forEach(image -> imageVideoRemover.removeFile(image.path()));
    }
}
