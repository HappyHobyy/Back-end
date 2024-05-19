package org.v1.implementaion.imagevideo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.external.ExternalImageVideoRepository;

@Component
@AllArgsConstructor
public class ImageVideoRemover {
    private final ExternalImageVideoRepository externalRepository;
    public void removeFile(String fileUrl) {
        externalRepository.removeFile(fileUrl);
    }
}
