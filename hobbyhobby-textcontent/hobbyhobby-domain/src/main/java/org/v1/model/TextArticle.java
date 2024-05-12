package org.v1.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TextArticle {
    private final Long id;
    private final Article article;
    private final Integer likes;
    public static TextArticle withoutId(Article article, Integer likes){
        return new TextArticle(null,article,likes);
    }
}
