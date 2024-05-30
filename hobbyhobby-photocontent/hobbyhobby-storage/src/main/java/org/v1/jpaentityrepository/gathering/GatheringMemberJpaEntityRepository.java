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
    public boolean appendGatheringMember(GatheringMember member) {
        switch (member.type()) {
            case UNION_GATHERING:
                int updatedUnionRows = unionGatheringJpaRepository.updatePlusCount(member.articleId());
                if (updatedUnionRows > 0) {
                    UnionGatheringJpaEntity entity= unionGatheringJpaRepository.findWithCommunityById(member.articleId());
                    joinedUnionGatheringJpaRepository.save(JoinedUnionGatheringJpaEntity.of(member,entity.getCommunity1(),entity.getCommunity2()));
                    return true;
                } else {
                    return false;
                }
            case SINGLE_GATHERING:
                int updatedSingleRows = gatheringJpaRepository.updatePlusCount(member.articleId());
                if (updatedSingleRows > 0) {
                    GatheringJpaEntity entity= gatheringJpaRepository.findWithCommunityById(member.articleId());
                    joinedGatheringJpaRepository.save(JoinedGatheringJpaEntity.of(member,entity.getCommunity()));
                    return true;
                } else {
                    return false;
                }
        }
        return false;
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
