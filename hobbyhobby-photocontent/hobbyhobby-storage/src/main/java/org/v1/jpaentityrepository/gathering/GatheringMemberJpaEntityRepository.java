package org.v1.jpaentityrepository.gathering;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.v1.jpaentity.gathering.GatheringJpaEntity;
import org.v1.jpaentity.gathering.JoinedGatheringJpaEntity;
import org.v1.jpaentity.gathering.JoinedUnionGatheringJpaEntity;
import org.v1.jpaentity.gathering.UnionGatheringJpaEntity;
import org.v1.jpaentity.user.UserJpaEntity;
import org.v1.jparepository.gathering.GatheringJpaRepository;
import org.v1.jparepository.gathering.JoinedGatheringJpaRepository;
import org.v1.jparepository.gathering.JoinedUnionGatheringJpaRepository;
import org.v1.jparepository.gathering.UnionGatheringJpaRepository;
import org.v1.model.group.GatheringMember;
import org.v1.repository.group.GatheringMemberRepository;

@Repository
@AllArgsConstructor
public class GatheringMemberJpaEntityRepository implements GatheringMemberRepository {
    private final JoinedGatheringJpaRepository joinedGatheringJpaRepository;
    private final GatheringJpaRepository gatheringJpaRepository;
    private final JoinedUnionGatheringJpaRepository joinedUnionGatheringJpaRepository;
    private final UnionGatheringJpaRepository unionGatheringJpaRepository;
    @Override
    public int appendGatheringMember(GatheringMember member) {
        switch (member.type()) {
            case UNION_GATHERING:
                UnionGatheringJpaEntity unionGatheringJpaEntity = unionGatheringJpaRepository.findWithCommunityById(member.articleId());
                joinedUnionGatheringJpaRepository.save(JoinedUnionGatheringJpaEntity.of(member, unionGatheringJpaEntity.getCommunity1(), unionGatheringJpaEntity.getCommunity2()));
                return unionGatheringJpaRepository.updatePlusCount(member.articleId());
            case SINGLE_GATHERING:
                GatheringJpaEntity gatheringJpaEntity = gatheringJpaRepository.findWithCommunityById(member.articleId());
                joinedGatheringJpaRepository.save(JoinedGatheringJpaEntity.of(member, gatheringJpaEntity.getCommunity()));
                return gatheringJpaRepository.updatePlusCount(member.articleId());
        }
        return 0;
    }

    @Override
    public void removeGatheringMember(GatheringMember member) {
        switch (member.type()) {
            case UNION_GATHERING:
                unionGatheringJpaRepository.updateMinusCount(member.articleId());
                joinedUnionGatheringJpaRepository.deleteByUnionGathering_IdAndUser_Id(member.articleId(), member.userId());
                break;
            case SINGLE_GATHERING:
                gatheringJpaRepository.updateMinusCount(member.articleId());
                joinedGatheringJpaRepository.deleteByGathering_IdAndUser_Id(member.articleId(), member.userId());
                break;
        }
    }
}
