package org.v1.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.Contents;
import org.v1.model.PhotoArticle;
import org.v1.repository.ContentRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class ContentUpdater {
    private final ContentRepository contentRepository;
    public void updatePhotoArticle(Contents.PhotoArticles photos){
        contentRepository.updatePhotoArticle(photos);
    }
}
