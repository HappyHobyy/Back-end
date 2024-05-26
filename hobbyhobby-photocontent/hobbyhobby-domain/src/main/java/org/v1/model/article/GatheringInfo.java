package org.v1.model.article;

import java.util.List;

public record GatheringInfo(ArticleType type, List<Integer> communityIds, Long articleId) {
    public static GatheringInfo singleGatheringWithCommunity(Integer communityId) {
        return new GatheringInfo(ArticleType.SINGLE_GATHERING, List.of(communityId),null);
    }
    public static GatheringInfo singleGatheringWithArticle(Long articleId) {
        return new GatheringInfo(ArticleType.SINGLE_GATHERING,null,articleId);
    }
    public static GatheringInfo unionGatheringWithCommunity(List<Integer> communityIds) {
        return new GatheringInfo(ArticleType.UNION_GATHERING, communityIds,null);
    }
    public static GatheringInfo unionGatheringWithArticle(Long articleId) {
        return new GatheringInfo(ArticleType.UNION_GATHERING,null,articleId);
    }
    public GatheringInfo initArticleId(Long articleId) {
        return new GatheringInfo(type,communityIds,articleId);
    }
}