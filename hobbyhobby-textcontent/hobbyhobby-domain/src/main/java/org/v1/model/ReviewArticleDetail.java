package org.v1.model;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ReviewArticleDetail {
    private final List<ReviewComment> comments;
    private final ReviewContent reviewContent;
    private final UserStatus userStatus;
}
