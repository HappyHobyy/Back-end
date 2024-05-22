package org.v1.rabbitmq;

import java.util.List;

public record GatheringArticleMessageList(
        List<GatheringArticleMessage> popularCommunityArticleMessages,
        List<GatheringArticleMessage> notPopularCommunityArticleMessages
) {
}
