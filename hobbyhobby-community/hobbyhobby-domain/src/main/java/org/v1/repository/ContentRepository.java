package org.v1.repository;

import org.v1.model.Community;

import java.time.LocalDateTime;

public interface ContentRepository {
    Integer readContentCount(Community community);
}
