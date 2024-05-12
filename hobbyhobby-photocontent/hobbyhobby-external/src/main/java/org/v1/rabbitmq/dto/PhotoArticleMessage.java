package org.v1.rabbitmq.dto;


import org.v1.model.PhotoArticle;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record PhotoArticleMessage(
        Long photoArticleId,
        LocalDateTime date,
        String nickname,
        Integer likes,
        Integer comments,
        String url

) {
    public static List<PhotoArticleMessage> of(List<PhotoArticle> photoArticleList) {
        if (photoArticleList == null) {
            return Collections.emptyList();
        }
        return photoArticleList.stream()
                .map(textContent -> new PhotoArticleMessage(
                        textContent.getId(),
                        textContent.getDate(),
                        textContent.getUser().nickname(),
                        textContent.getLikesComments().likes(),
                        textContent.getLikesComments().comments(),
                        textContent.getMainImageUrl()
                ))
                .collect(Collectors.toList());
    }
}
