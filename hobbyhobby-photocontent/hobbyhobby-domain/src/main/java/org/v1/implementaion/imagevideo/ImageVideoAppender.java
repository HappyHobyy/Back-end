package org.v1.implementaion.imagevideo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.external.ExternalImageVideoRepository;

import java.io.File;

@Component
@AllArgsConstructor
public class ImageVideoAppender {
    private final ExternalImageVideoRepository externalRepository;
    public String appendFile(File file, String dirName) {
        return externalRepository.uploadFile(file,dirName);
    }
}
