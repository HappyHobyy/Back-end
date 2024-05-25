package org.v1.implementaion.article;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.article.PhotoArticle;
import org.v1.model.imageVideo.ImageVideo;
import org.v1.repository.article.PhotoArticleRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class PhotoArticleReader {
    private PhotoArticleRepository repository;
    public List<PhotoArticle> readTenArticleLatest(int index,Integer communityId) {
        return repository.readArticleLatest(index,communityId);
    }
    public List<PhotoArticle> readTenArticleLikes(int index,Integer communityId) {
        return repository.readArticleLikes(index,communityId);
    }
    public List<PhotoArticle> readPopularCommunityHotArticle(Integer communityId) {
        return repository.readPopularCommunityArticle(communityId);
    }
    public List<PhotoArticle> readNotPopularCommunityHotArticle(Integer communityId) {
        return repository.readNotPopularCommunityArticle(communityId);
    }
    public List<ImageVideo> readImageList(Long photoArticleId) {
        return repository.readImageList(photoArticleId);
    }

}
