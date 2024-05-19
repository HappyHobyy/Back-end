package org.v1.rabbitmq;

import java.util.List;

public record PhotoArticleMessageList(
        List<PhotoArticleMessage> popularCommunityArticleMessages,
        List<PhotoArticleMessage> notPopularCommunityArticleMessages
) {

}