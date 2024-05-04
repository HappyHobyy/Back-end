package org.v1.dto;

import org.v1.model.Article;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record ArticleResponse(
        Long articleId,
        String title,
        LocalDateTime date,
        String userName,
        Long userId,
        Integer likes
) {
    public static List<ArticleResponse> of(List<Article> articleList) {
        if (articleList == null) {
            return Collections.emptyList();
        }

        return articleList.stream()
                .map(textContent -> new ArticleResponse(
                        textContent.getId(),
                        textContent.getTitle(),
                        textContent.getDate(),
                        textContent.getUser().nickname(),
                        textContent.getUser().id(),
                        textContent.getLikes()
                ))
                .collect(Collectors.toList());
    }
}
