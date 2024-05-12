package org.v1.rabbitmq;


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
                .map(photoArticle -> new PhotoArticleMessage(
                        photoArticle.getId(),
                        photoArticle.getDate(),
                        photoArticle.getUser().nickname(),
                        photoArticle.getLikesComments().likes(),
                        photoArticle.getLikesComments().comments(),
                        photoArticle.getMainImageUrl()
                ))
                .collect(Collectors.toList());
    }
}
