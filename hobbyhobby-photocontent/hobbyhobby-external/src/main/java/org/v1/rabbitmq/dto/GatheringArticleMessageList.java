package org.v1.rabbitmq.dto;

import org.v1.model.article.GatheringArticle;

import java.util.List;

public record GatheringArticleMessageList(
        List<GatheringArticleMessage> popularCommunityArticleMessages,
        List<GatheringArticleMessage> notPopularCommunityArticleMessages
) {
}
