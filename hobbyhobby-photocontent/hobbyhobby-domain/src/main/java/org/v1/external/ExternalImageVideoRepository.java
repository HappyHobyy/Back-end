package org.v1.external;

import java.io.File;

public interface ExternalImageVideoRepository {
    String uploadFile(File uploadFile, String dirName);
    void removeFile(String fileUrl);
}
