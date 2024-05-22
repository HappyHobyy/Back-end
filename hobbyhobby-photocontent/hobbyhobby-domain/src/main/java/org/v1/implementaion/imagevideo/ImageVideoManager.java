package org.v1.implementaion.imagevideo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.imageVideo.ImageVideo;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ImageVideoManager {
    private final ImageVideoRemover imageVideoRemover;
    private final ImageVideoAppender imageVideoAppender;
    public List<ImageVideo> appendFiles(Long id, List<ImageVideo> files) {
        return files.stream()
                .map(file -> {
                    String path = imageVideoAppender.appendFile(file.data(), file.fileType() + id.toString());
                    return ImageVideo.withoutData(file.index(), path,file.fileType());
                })
                .collect(Collectors.toList());
    }
    public void removeImages(List<ImageVideo> images) {
        images.forEach(image -> imageVideoRemover.removeFile(image.path()));
    }
    public ImageVideo appendFile(Long id, ImageVideo file){
        String path = imageVideoAppender.appendFile(file.data(), file.fileType() + id.toString());
        return ImageVideo.withoutData(file.index(), path,file.fileType());
    }
    public void removeImage(ImageVideo image) {
        imageVideoRemover.removeFile(image.path());
    }
}
