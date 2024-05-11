package org.v1.global.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.v1.model.Content;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Component
@AllArgsConstructor
public class FileUtil {
    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            throw new IllegalArgumentException("File name cannot be null");
        }
        File convertFile = new File(originalFilename);
        if (!convertFile.createNewFile()) {
            throw new IOException("Failed to create file: " + convertFile.getName());
        }
        try (FileOutputStream fos = new FileOutputStream(convertFile)) {
            fos.write(file.getBytes());
        }
        return convertFile;
    }

    public List<Content.Image> convertMultipartFiles(List<MultipartFile> multipartFiles) {
        return multipartFiles != null ?
                multipartFiles.stream()
                        .map(file -> {
                            try {
                                File convertedFile = convertMultipartFileToFile(file);
                                return Content.Image.withoutPath(multipartFiles.indexOf(file), convertedFile);
                            } catch (IOException e) {
                                throw new RuntimeException("MultipartFile -> File로 전환이 실패했습니다.", e);
                            }
                        })
                        .collect(Collectors.toList()) :
                Collections.emptyList();
    }
    public Content.Image convertMultipartFile(MultipartFile multipartFile) {
        try {
            return multipartFile != null ? Content.Image.withoutPath(0, convertMultipartFileToFile(multipartFile)) : null;
        } catch (IOException e) {
            throw new RuntimeException("MultipartFile을 File로 변환하는데 실패했습니다.", e);
        }
    }
}