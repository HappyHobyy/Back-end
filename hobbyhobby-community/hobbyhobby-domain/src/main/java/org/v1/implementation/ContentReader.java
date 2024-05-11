package org.v1.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.Community;
import org.v1.model.Contents;
import org.v1.model.GroupArticle;
import org.v1.model.PhotoArticle;
import org.v1.repository.ContentRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class ContentReader {
    private final ContentRepository contentRepository;
    public Contents.PhotoArticles readPopularPhotoContent(Community populistCommunity) {
        return contentRepository.readPopularPhotoContent(populistCommunity);
    }
    public Contents.GroupArticles readPopularGroupContent(Community populistCommunity) {
        return contentRepository.readPopularGroupContent(populistCommunity);
    }
}
