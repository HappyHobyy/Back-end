package org.v1.jpaentityrepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.v1.error.BusinessException;
import org.v1.error.ErrorCode;
import org.v1.jpaentity.CommunityJpaEntity;
import org.v1.jpaentity.CommunityUserJpaEntity;
import org.v1.jpaentity.LikedCommunityJpaEntity;
import org.v1.jparepository.LikeCommunityJpaRepository;
import org.v1.model.user.User;
import org.v1.repository.UserCommunityRepository;

@Repository
@AllArgsConstructor
public class LikeCommunityJpaEntityRepository implements UserCommunityRepository {
    private final LikeCommunityJpaRepository likeCommunityJpaRepository;

    @Override
    public void appendUserToCommunity(Long userId, Long communityId) {
        if(likeCommunityJpaRepository.existsByCommunity_IdAndUser_Id(userId, communityId)) {
            throw new BusinessException(ErrorCode.COMMUNITY_ALREADY_LIKED);
        } else {
            likeCommunityJpaRepository.save(LikedCommunityJpaEntity.of(userId, communityId));
        }
    }

    @Override
    public void removeUserFromCommunity(Long userId, Long communityId) {
        likeCommunityJpaRepository.deleteByUser_IdAndCommunity_Id(userId, communityId);
    }
}
