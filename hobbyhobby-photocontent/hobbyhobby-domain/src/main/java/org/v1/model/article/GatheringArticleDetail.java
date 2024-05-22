package org.v1.model.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.v1.model.article.GatheringArticleContent;
import org.v1.model.user.UserStatus;

@Getter
@AllArgsConstructor
public class GatheringArticleDetail {
    private final GatheringArticleContent content;
    private final UserStatus userStatus;
}