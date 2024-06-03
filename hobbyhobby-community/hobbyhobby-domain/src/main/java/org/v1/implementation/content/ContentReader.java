package org.v1.implementation.content;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.community.Community;
import org.v1.model.content.Contents;
import org.v1.repository.ContentRepository;

@Component
@AllArgsConstructor
public class ContentReader {
    private final ContentRepository contentRepository;
    public Contents.PhotoArticles readPopularPhotoContent() {
        return contentRepository.readPopularPhotoContent();
    }
    public Contents.GatheringArticles readPopularGatheringContent() {
        return contentRepository.readPopularGatheringContent();
    }
}
