package org.v1.repository.article;

import org.v1.model.article.GatheringArticle;
import org.v1.model.article.PhotoArticle;
import org.v1.model.article.PhotoAriticleContent;
import org.v1.model.imageVideo.ImageVideo;
import org.v1.model.user.UserStatus;

import java.util.List;

public interface PhotoArticleRepository {
    List<PhotoArticle> readArticleLatest(Integer communityId);
    List<PhotoArticle> readArticleLikes(Integer communityId);
    long appendArticle(PhotoArticle photoArticle);
    List<PhotoArticle> readPopularCommunityArticle(Integer communityId);
    List<PhotoArticle> readNotPopularCommunityArticle(Integer communityId);
    void appendArticleContent(List<ImageVideo> imageVideoList, Long articleId);
    void removeArticle(Long photoArticleId);
    PhotoAriticleContent readContent(Long articleId);
}
