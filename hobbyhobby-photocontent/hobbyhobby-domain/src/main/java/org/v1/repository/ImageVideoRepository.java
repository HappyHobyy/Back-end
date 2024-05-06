package org.v1.repository;

import java.io.File;

public interface ImageVideoRepository {
    String uploadFile(File uploadFile, String dirName);
    void removeFile(String fileUrl);
}
