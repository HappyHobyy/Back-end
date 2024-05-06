package org.v1.implementaion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.repository.ImageVideoRepository;

@Component
@AllArgsConstructor
public class ImageVideoRemover {
    private final ImageVideoRepository imageVideoRepository;
    public void removeFile(String fileUrl) {
        imageVideoRepository.removeFile(fileUrl);
    }
}
