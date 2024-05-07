package org.v1.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.Community;
import org.v1.repository.ContentRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class ContentReader {
    private final ContentRepository contentRepository;
    public Integer readTextContentCount(Community community) {
        return contentRepository.readContentCount(community);
    }
}
