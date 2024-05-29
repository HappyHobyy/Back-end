package org.v1.repository.article;

import org.v1.model.article.PhotoArticle;
import org.v1.model.imageVideo.ImageVideo;

import java.util.List;

public interface PhotoArticleRepository {
    List<PhotoArticle> readArticleLatest(int index,Integer communityId);
    List<PhotoArticle> readArticleLikes(int index,Integer communityId);
    long appendArticle(PhotoArticle photoArticle);
    List<PhotoArticle> readPopularCommunityArticle(Integer communityId);
    List<PhotoArticle> readNotPopularCommunityArticle(Integer communityId);
    void appendArticleContent(List<ImageVideo> imageVideoList, Long articleId);
    void removeArticle(Long photoArticleId);
    List<ImageVideo> readImageList(Long articleId);
}
