package org.v1.implementaion.group;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.error.BusinessException;
import org.v1.error.ErrorCode;
import org.v1.model.group.GatheringMember;
import org.v1.repository.group.GatheringMemberRepository;

@Component
@AllArgsConstructor
public class GatheringMemberAppender {
    private final GatheringMemberRepository repository;
    public void appendGatheringMember(GatheringMember member) {
         if(!repository.appendGatheringMember(member)){
             throw new BusinessException(ErrorCode.GATHERING_CANNOT_APPEND);
         }
    }
}
