package org.v1.implementaion.group;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.v1.model.group.GatheringMember;
import org.v1.repository.group.GatheringMemberRepository;

@Component
@AllArgsConstructor
public class GatheringMemberRemover {
    private final GatheringMemberRepository repository;
    @Transactional
    public void removeGatheringMember(GatheringMember member) {
        repository.removeGatheringMember(member);
    }
}
