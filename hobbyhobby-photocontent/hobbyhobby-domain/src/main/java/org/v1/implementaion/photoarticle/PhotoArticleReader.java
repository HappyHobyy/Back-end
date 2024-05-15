package org.v1.implementaion.photoarticle;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.photoartlcle.PhotoAriticleContent;
import org.v1.model.photoartlcle.PhotoArticle;
import org.v1.repository.PhotoArticleRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class PhotoArticleReader {
    private PhotoArticleRepository photoArticleRepository;
    public List<PhotoArticle> readTenArticleLatest(Integer communityId) {
        return photoArticleRepository.readArticleLatest(communityId);
    }
    public List<PhotoArticle> readTenArticleLikes(Integer communityId) {
        return photoArticleRepository.readArticleLikes(communityId);
    }
    public List<PhotoArticle> readPopularCommunityHotArticle(Integer communityId) {
        return photoArticleRepository.readArticleLikes(communityId);
    }
    public List<PhotoArticle> readNotPopularCommunityHotArticle(Integer communityId) {
        return photoArticleRepository.readArticleLikes(communityId);
    }
    public PhotoAriticleContent readContent(Long photoArticleId) {
        return photoArticleRepository.readContent(photoArticleId);
    }

}
