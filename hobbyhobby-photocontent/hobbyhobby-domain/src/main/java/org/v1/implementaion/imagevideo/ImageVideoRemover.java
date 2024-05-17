package org.v1.implementaion.imagevideo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.external.ExternalImageVideoRepository;

@Component
@AllArgsConstructor
public class ImageVideoRemover {
    private final ExternalImageVideoRepository externalImageVideoRepository;
    public void removeFile(String fileUrl) {
        externalImageVideoRepository.removeFile(fileUrl);
    }
}
