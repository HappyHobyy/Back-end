package org.v1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TextArticleDetail {
    private final List<TextComment> comments;
    private final Content content;
    private final UserStatus userStatus;
}
