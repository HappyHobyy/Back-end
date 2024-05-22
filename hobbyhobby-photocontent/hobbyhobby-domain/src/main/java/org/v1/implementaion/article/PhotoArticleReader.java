package org.v1.implementaion.article;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.article.PhotoAriticleContent;
import org.v1.model.article.PhotoArticle;
import org.v1.repository.article.PhotoArticleRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class PhotoArticleReader {
    private PhotoArticleRepository repository;
    public List<PhotoArticle> readTenArticleLatest(Integer communityId) {
        return repository.readArticleLatest(communityId);
    }
    public List<PhotoArticle> readTenArticleLikes(Integer communityId) {
        return repository.readArticleLikes(communityId);
    }
    public List<PhotoArticle> readPopularCommunityHotArticle(Integer communityId) {
        return repository.readPopularCommunityArticle(communityId);
    }
    public List<PhotoArticle> readNotPopularCommunityHotArticle(Integer communityId) {
        return repository.readNotPopularCommunityArticle(communityId);
    }
    public PhotoAriticleContent readContent(Long photoArticleId) {
        return repository.readContent(photoArticleId);
    }

}
