package org.v1.implementaion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.repository.S3Repository;

import java.awt.*;
import java.io.File;

@Component
@AllArgsConstructor
public class ImageAppender {
    private final S3Repository s3Repository;
    public String appendImage(File file, String dirName) {
        return s3Repository.uploadImage(file,dirName);
    }
}
