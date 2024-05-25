package org.v1.global.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class FileUtil {
    public static File convertMultipartFileToFile(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString();
        File convertFile = new File(fileName);
        if (!convertFile.createNewFile()) {
            throw new IOException("Failed to create file: " + convertFile.getName());
        }
        try (FileOutputStream fos = new FileOutputStream(convertFile)) {
            fos.write(file.getBytes());
        }
        return convertFile;
    }
}