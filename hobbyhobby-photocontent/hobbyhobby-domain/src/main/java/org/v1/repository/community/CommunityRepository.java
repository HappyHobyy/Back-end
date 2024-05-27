package org.v1.repository.community;

import org.v1.model.article.ArticleType;

public interface CommunityRepository {
    void plusCommunityLikes(final Integer targetCommunityId, ArticleType articleType);
    void minusCommunityLikes(final Integer targetCommunityId,ArticleType articleType);
    void resetCommunityLikes();
    int readPopulistCommunity();
}
