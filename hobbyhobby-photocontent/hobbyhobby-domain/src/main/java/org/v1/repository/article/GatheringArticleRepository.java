package org.v1.repository.article;

import org.v1.model.article.ArticleType;
import org.v1.model.article.GatheringInfo;
import org.v1.model.article.GatheringArticle;
import org.v1.model.article.GatheringArticleContent;
import org.v1.model.user.UserStatus;

import java.util.List;

public interface GatheringArticleRepository {
    List<GatheringArticle> readArticleLatest(ArticleType type);
    List<GatheringArticle> readArticleSearch(GatheringInfo info);
    List<GatheringArticle> readPopularCommunityArticle(Integer communityId);
    List<GatheringArticle> readNotPopularCommunityArticle(Integer communityId);
    Long appendArticle(GatheringArticle photoArticle);
    void appendArticleContent(GatheringArticleContent content, GatheringInfo info);
    void removeArticle(GatheringInfo info);
    UserStatus checkArticleUserRelation(GatheringInfo info, Long userId);
    boolean checkArticleExist(GatheringInfo info);
    GatheringArticleContent readContent(GatheringInfo info);
}
