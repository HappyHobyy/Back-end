package org.v1.model.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.v1.model.imageVideo.ImageVideo;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class GatheringArticleContent {
    private final String description;
    private final String location;
    private final LocalDateTime gatheringTime;
    private final ImageVideo image;
    private final String gatheringUrl;
    public GatheringArticleContent updateImage(ImageVideo image) {
        return new GatheringArticleContent(this.description,this.location,this.gatheringTime,image,gatheringUrl);
    }
}
