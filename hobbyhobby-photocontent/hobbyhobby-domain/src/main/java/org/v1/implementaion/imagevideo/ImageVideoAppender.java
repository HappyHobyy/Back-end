package org.v1.implementaion.imagevideo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.repository.ImageVideoRepository;

import java.io.File;

@Component
@AllArgsConstructor
public class ImageVideoAppender {
    private final ImageVideoRepository imageVideoRepository;
    public String appendFile(File file, String dirName) {
        return imageVideoRepository.uploadFile(file,dirName);
    }
}
