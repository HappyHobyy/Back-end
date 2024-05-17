package org.v1.implementaion.imagevideo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.external.ExternalImageVideoRepository;

import java.io.File;

@Component
@AllArgsConstructor
public class ImageVideoAppender {
    private final ExternalImageVideoRepository externalImageVideoRepository;
    public String appendFile(File file, String dirName) {
        return externalImageVideoRepository.uploadFile(file,dirName);
    }
}
