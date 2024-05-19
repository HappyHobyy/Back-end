package org.v1.service.group;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementaion.group.GatheringMemberAppender;
import org.v1.implementaion.group.GatheringMemberRemover;
import org.v1.model.group.GatheringMember;

@Service
@AllArgsConstructor
public class GatheringMemberService {
    private final GatheringMemberAppender gatheringAppender;
    private final GatheringMemberRemover gatheringMemberRemover;
    public void leaveGathering(GatheringMember member) {
        gatheringMemberRemover.removeGatheringMember(member);
    }
    public void joinGathering(GatheringMember member) {
        gatheringAppender.appendGatheringMember(member);
    }

}
