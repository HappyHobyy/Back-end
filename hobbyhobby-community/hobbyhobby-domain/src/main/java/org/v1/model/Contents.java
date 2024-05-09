package org.v1.model;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Contents {
    PhotoArticles photoArticles;
    GroupArticles groupArticles;
    public record PhotoArticles(List<PhotoArticle> popularPhotoArticle, List<PhotoArticle> nonePopular){}
    public record GroupArticles(List<GroupArticle> popularGroupArticle, List<GroupArticle> nonePopularGroupArticle){}
}
