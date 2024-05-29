package org.v1.rabbitmq.dto;

import java.util.List;

public record GatheringArticleMessageList(
        List<GatheringArticleMessage> popularCommunityArticleMessages,
        List<GatheringArticleMessage> notPopularCommunityArticleMessages
) {
}
