package org.v1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@AllArgsConstructor
public class Content {
    private final String text;
    private final List<ImageVideo> images;

    public record ImageVideo(Integer index, String path, File data, FileType fileType) {
        public static ImageVideo withoutPath(Integer index, File data,FileType fileType) {
            return new ImageVideo(index,null, data,fileType);
        }
        public static ImageVideo withoutData(Integer index, String path,FileType fileType) {
            return new ImageVideo(index,path, null,fileType);
        }
    }
    public enum FileType {
        H_LOG
    }
}
