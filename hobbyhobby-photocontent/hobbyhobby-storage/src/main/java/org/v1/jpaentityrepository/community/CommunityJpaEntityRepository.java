package org.v1.jpaentityrepository.community;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.v1.jparepository.CommunityJpaRepository;
import org.v1.model.article.ArticleType;
import org.v1.repository.community.CommunityRepository;
@Repository
@AllArgsConstructor
public class CommunityJpaEntityRepository implements CommunityRepository {
    private final CommunityJpaRepository communityJpaRepository;
    @Override
    public void plusCommunityLikes(Integer targetCommunityId,ArticleType articleType) {
        communityJpaRepository.increaseLikeCount(targetCommunityId.longValue());
    }
    @Override
    public void minusCommunityLikes(Integer targetCommunityId, ArticleType articleType) {
        communityJpaRepository.decreaseLikeCount(targetCommunityId.longValue());
    }

    @Override
    public void resetCommunityLikes() {
        communityJpaRepository.resetAllLikeCount();
    }
    @Override
    public int readPopulistCommunity() {
        return communityJpaRepository.findTopByOrderByCommunityLikeCountDesc().getId().intValue();
    }
}
