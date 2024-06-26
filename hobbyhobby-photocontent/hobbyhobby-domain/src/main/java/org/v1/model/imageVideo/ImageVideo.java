package org.v1.model.imageVideo;

import java.io.File;

public record ImageVideo(Integer index, String path, File data, FileType fileType) {
    public static ImageVideo withoutPath(Integer index, File data, FileType fileType) {
        return new ImageVideo(index,null, data,fileType);
    }
    public static ImageVideo withoutData(Integer index, String path, FileType fileType) {
        return new ImageVideo(index,path, null,fileType);
    }
    public static ImageVideo withOnlyPath(String path) {
        return new ImageVideo(null,path, null,null);
    }
    public enum FileType {
        H_LOG,
        UNION_GATHERING,
        GATHERING
    }
}