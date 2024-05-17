package org.v1.implementaion.imagevideo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.photo.PhotoAriticleContent;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ImageVideoProcessor {
    private final ImageVideoRemover imageVideoRemover;
    private final ImageVideoAppender imageVideoAppender;
    public List<PhotoAriticleContent.ImageVideo> appendFiles(Long id, List<PhotoAriticleContent.ImageVideo> files) {
        return files.stream()
                .map(file -> {
                    String path = imageVideoAppender.appendFile(file.data(), file.fileType() + id.toString());
                    return PhotoAriticleContent.ImageVideo.withoutData(file.index(), path,file.fileType());
                })
                .collect(Collectors.toList());
    }
    public void removeImages(List<PhotoAriticleContent.ImageVideo> images) {
        images.forEach(image -> imageVideoRemover.removeFile(image.path()));
    }
}
