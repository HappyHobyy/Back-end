package org.v1.jpaentityrepository.gathering;

import org.springframework.stereotype.Repository;
import org.v1.model.group.GatheringMember;
import org.v1.repository.group.GatheringMemberRepository;
@Repository
public class GatheringMemberJpaEntityRepository implements GatheringMemberRepository {

    @Override
    public void appendGatheringMember(GatheringMember member) {

    }
    @Override
    public void removeGatheringMember(GatheringMember member) {

    }
}
