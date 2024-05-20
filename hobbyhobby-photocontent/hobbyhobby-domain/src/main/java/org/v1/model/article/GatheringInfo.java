package org.v1.model.article;

import java.util.List;

public record GatheringInfo(ArticleType type, List<Integer> communityIds, Long articleId) {
    public static GatheringInfo singleGatheringWithCommunity(Integer communityId) {
        return new GatheringInfo(ArticleType.SINGLE_GATHERING, List.of(communityId),null);
    }
    public static GatheringInfo multiGatheringWithCommunity(List<Integer> communityIds) {
        return new GatheringInfo(ArticleType.MULTI_GATHERING, communityIds,null);
    }
    public static GatheringInfo multiGatheringWithArticle(Long articleId) {
        return new GatheringInfo(ArticleType.MULTI_GATHERING,null,articleId);
    }
    public static GatheringInfo singleGatheringWithArticle(Long articleId) {
        return new GatheringInfo(ArticleType.SINGLE_GATHERING,null,articleId);
    }

}
