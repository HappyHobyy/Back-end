package org.v1.domain.user.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.domain.user.repository.ImageRepository;

import java.io.File;

@Component
@AllArgsConstructor
public class ImageAppender {
    private final ImageRepository imageRepository;
    public String appendImage(File file, String dirName) {
        return imageRepository.uploadImage(file,dirName);
    }
}
