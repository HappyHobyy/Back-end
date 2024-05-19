package org.v1.dto.request;


import org.v1.model.article.ArticleType;
import org.v1.model.group.GatheringMember;

public record GatheringMemberRequest(
        Long articleId,
        ArticleType type
) {
    public GatheringMember toGatheringMember(Long userId) {
        return new GatheringMember(articleId,userId, type);
    }
}
