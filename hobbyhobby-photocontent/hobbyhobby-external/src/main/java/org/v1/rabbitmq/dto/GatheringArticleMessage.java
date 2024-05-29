package org.v1.rabbitmq.dto;

import org.v1.model.article.GatheringArticle;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record GatheringArticleMessage(
        Long gatheringArticleId,
        LocalDateTime date,
        String title,
        String nickname,
        Integer joinedMax,
        Integer joinedCount,
        Integer likes,
        String url,
        Long userId,
        List<Integer> communityIds
) {
    public static List<GatheringArticleMessage> of(List<GatheringArticle> gatheringArticleList) {
        if (gatheringArticleList == null) {
            return Collections.emptyList();
        }
        return gatheringArticleList.stream()
                .map(gatheringArticle -> new GatheringArticleMessage(
                        gatheringArticle.getId(),
                        gatheringArticle.getCreatedAt(),
                        gatheringArticle.getTitle(),
                        gatheringArticle.getUser().nickname(),
                        gatheringArticle.getJoinedMax(),
                        gatheringArticle.getJoinedCount(),
                        gatheringArticle.getLikes(),
                        gatheringArticle.getImageVideo().path(),
                        gatheringArticle.getUser().id(),
                        gatheringArticle.getInfo().communityIds()
                ))
                .collect(Collectors.toList());
    }
}