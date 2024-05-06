package org.v1.repository;

import java.io.File;

public interface ImageRepository {
    String uploadImage(File uploadFile, String dirName);
    void removeImage(String fileUrl);
}
