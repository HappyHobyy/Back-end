package org.v1.rabbitmq;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.v1.model.Community;
import org.v1.repository.ContentRepository;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class ExternalContentHandler implements ContentRepository {
    private final ExternalTextContentHandler textContentHandler;
    private final ExternalPhotoContentHandler photoContentHandler;
    @Override
    public Integer readContentCount(Community community) {
        return null;
    }
}
