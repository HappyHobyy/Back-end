package org.v1.rabbitmq.dto;

import org.v1.model.article.GatheringArticle;
import org.v1.model.article.PhotoArticle;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record GatheringArticleMessage (
        Long photoArticleId,
        LocalDateTime date,
        String nickname,
        Integer usersCount,
        Integer likes,
        String url,
        Long userId,
        String userName,
        String userImageUrl,
        List<Integer> communityId
){
    public static List<GatheringArticleMessage> of(List<GatheringArticle> gatheringArticleList) {
        if (gatheringArticleList == null) {
            return Collections.emptyList();
        }
        return gatheringArticleList.stream()
                .map(gatheringArticle -> new GatheringArticleMessage(
                        gatheringArticle.getId(),
                        gatheringArticle.getDate(),
                        gatheringArticle.getUser().nickname(),
                        gatheringArticle.getLikes(),
                        gatheringArticle.getLikes(),
                        gatheringArticle.getImageVideo().path(),
                        gatheringArticle.getUser().id(),
                        gatheringArticle.getUser().nickname(),
                        gatheringArticle.getUser().imageUrl(),
                        null
                ))
                .collect(Collectors.toList());
    }
}