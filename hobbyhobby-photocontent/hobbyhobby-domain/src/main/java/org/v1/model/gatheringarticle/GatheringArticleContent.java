package org.v1.model.gatheringarticle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.v1.model.photoartlcle.PhotoAriticleContent;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@AllArgsConstructor
public class GatheringArticleContent {
    private final String description;
    private final String location;
    private final LocalDateTime localDateTime;
    private final Image image;
    public record Image(Integer index, String path, File data, FileType fileType) {
        public static Image withoutPath(Integer index, File data, FileType fileType) {
            return new Image(index,null, data,fileType);
        }
        public static Image withoutData(Integer index, String path, FileType fileType) {
            return new Image(index,path, null,fileType);
        }
    }
    public enum FileType {
        GATHERING
    }
}
