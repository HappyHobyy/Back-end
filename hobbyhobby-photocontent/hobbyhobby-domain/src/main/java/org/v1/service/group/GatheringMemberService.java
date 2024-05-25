package org.v1.service.group;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementaion.article.GatheringArticleChecker;
import org.v1.implementaion.group.GatheringMemberAppender;
import org.v1.implementaion.group.GatheringMemberRemover;
import org.v1.model.group.GatheringMember;

@Service
@AllArgsConstructor
public class GatheringMemberService {
    private final GatheringMemberAppender gatheringAppender;
    private final GatheringArticleChecker gatheringArticleChecker;
    private final GatheringMemberRemover gatheringMemberRemover;
    public void leaveGathering(GatheringMember member) {
        gatheringArticleChecker.checkArticleToLeave(member);
        gatheringMemberRemover.removeGatheringMember(member);
    }
    public void joinGathering(GatheringMember member) {
        gatheringArticleChecker.checkArticleToJoined(member);
        gatheringAppender.appendGatheringMember(member);
    }

}
