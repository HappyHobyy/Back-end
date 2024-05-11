package org.v1.rabbitmq;

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
public class ExternalContentHandler implements ContentRepository {
    private final ExternalTextContentHandler textContentHandler;
    private final ExternalPhotoContentHandler photoContentHandler;
    @Override
    public Contents.PhotoArticles readPopularPhotoContent(Community community) {
        return null;
    }

    @Override
    public Contents.GroupArticles readPopularGroupContent(Community community) {
        return null;
    }
}
