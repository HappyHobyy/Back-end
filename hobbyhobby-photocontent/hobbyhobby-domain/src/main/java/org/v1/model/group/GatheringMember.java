package org.v1.model.group;

import org.v1.model.article.ArticleType;

public record GatheringMember(
        Long articleId,
        Long userId,
        ArticleType type
) {
}
