package org.v1.repository;

import java.io.File;

public interface S3Repository {
    String uploadImage(File uploadFile, String dirName);
}
