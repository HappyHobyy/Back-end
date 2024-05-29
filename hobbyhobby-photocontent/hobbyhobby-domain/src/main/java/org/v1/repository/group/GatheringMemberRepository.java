package org.v1.repository.group;

import org.v1.model.group.GatheringMember;

public interface GatheringMemberRepository {
    boolean appendGatheringMember(GatheringMember member);
    void removeGatheringMember(GatheringMember member);
}
