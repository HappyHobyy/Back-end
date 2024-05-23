package org.v1.model.content;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Contents {
    PhotoArticles photoArticles;
    GatheringArticles gatheringArticles;
    public record PhotoArticles(List<PhotoArticle> popularPhotoArticle, List<PhotoArticle> nonePopularPhotoArticle){}
    public record GatheringArticles(List<GatheringArticle> popularGatheringArticle, List<GatheringArticle> nonePopularGatheringArticle){}
}
