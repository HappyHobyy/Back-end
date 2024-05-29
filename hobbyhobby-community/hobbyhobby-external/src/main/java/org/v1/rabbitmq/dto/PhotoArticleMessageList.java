package org.v1.rabbitmq.dto;

import java.util.List;

public record PhotoArticleMessageList(
        List<PhotoArticleMessage> popularCommunityArticleMessages,
        List<PhotoArticleMessage> notPopularCommunityArticleMessages
) {

}