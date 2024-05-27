package org.v1.implementaion.article;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.error.BusinessException;
import org.v1.error.ErrorCode;
import org.v1.model.article.GatheringInfo;
import org.v1.model.group.GatheringMember;
import org.v1.repository.article.GatheringArticleRepository;

@Component
@AllArgsConstructor
public class GatheringArticleChecker {
    private final GatheringArticleRepository repository;
    public boolean isArticleUserOwner(Long userId, GatheringInfo info) {
        return repository.isArticleUserOwner(info, userId);
    }
    public boolean isArticleUserJoined(Long userId, GatheringInfo info) {
        return repository.isArticleUserJoined(info, userId);
    }

    public void checkArticleToJoined(GatheringMember member) {
        if(repository.checkGatheringMemberJoined(member)){
            throw new BusinessException(ErrorCode.GATHERING_ALREADY_JOINED);
        };
    }
    public void checkArticleToLeave(GatheringMember member) {
        if(!repository.checkGatheringMemberJoined(member)){
            throw new BusinessException(ErrorCode.GATHERING_ALREADY_LEAVED);
        };
    }
}
