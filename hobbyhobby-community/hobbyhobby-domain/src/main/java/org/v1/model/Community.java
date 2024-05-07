package org.v1.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Community {
    private final Long id;
    private final String communityName;
    private final String imageUrl;
    private final LocalDateTime latestUserAccessTime;
}
