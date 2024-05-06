package org.v1.domain.user.repository;

import java.io.File;

public interface ImageRepository {
    String uploadImage(File uploadFile, String dirName);
    void removeImage(String fileUrl);
}
