package org.v1.implementaion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.repository.S3Repository;

import java.io.File;

@Component
@AllArgsConstructor
public class ImageRemover {
    private final S3Repository s3Repository;
    public void removeImage(String fileUrl) {
        s3Repository.removeImage(fileUrl);
    }
}
