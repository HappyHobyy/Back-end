package org.v1.implementaion.article;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.article.ArticleType;
import org.v1.model.article.GatheringInfo;
import org.v1.model.article.GatheringArticle;
import org.v1.model.article.GatheringArticleContent;
import org.v1.repository.article.GatheringArticleRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class GatheringArticleReader {
    private GatheringArticleRepository repository;
    public List<GatheringArticle> readLatestArticles(ArticleType type) {
        return repository.readArticleLatest(type);
    }
    public List<GatheringArticle> readSearchArticles(GatheringInfo info) {
        return repository.readArticleSearch(info);
    }
    public List<GatheringArticle> readPopularCommunityHotArticle(Integer communityId) {
        return repository.readPopularCommunityArticle(communityId);
    }
    public List<GatheringArticle> readNotPopularCommunityHotArticle(Integer communityId) {
        return repository.readNotPopularCommunityArticle(communityId);
    }
    public GatheringArticleContent readContent(GatheringInfo info) {
        return repository.readContent(info);
    }
}
