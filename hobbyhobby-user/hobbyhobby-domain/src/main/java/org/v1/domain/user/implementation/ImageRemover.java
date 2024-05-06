package org.v1.domain.user.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.domain.user.repository.ImageRepository;

@Component
@AllArgsConstructor
public class ImageRemover {
    private final ImageRepository imageRepository;
    public void removeImage(String fileUrl) {
        imageRepository.removeImage(fileUrl);
    }
}
