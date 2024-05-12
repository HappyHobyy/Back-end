package org.v1.implementation.content;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.content.Contents;
import org.v1.repository.ContentRepository;

@Component
@AllArgsConstructor
public class ContentUpdater {
    private final ContentRepository contentRepository;
    public void updatePhotoArticle(Contents.PhotoArticles photos){
        contentRepository.updatePhotoArticle(photos);
    }
}
