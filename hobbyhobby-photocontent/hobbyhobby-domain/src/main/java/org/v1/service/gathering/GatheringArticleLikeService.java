package org.v1.service.gathering;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementaion.like.LikeAppender;
import org.v1.implementaion.like.LikeChecker;
import org.v1.implementaion.like.LikeEventPublisher;
import org.v1.implementaion.like.LikeRemover;
import org.v1.model.like.Like;

@Service
@AllArgsConstructor
public class GatheringArticleLikeService {
    private final LikeAppender likeAppender;
    private final LikeChecker likeChecker;
    private final LikeRemover likeRemover;
    private final LikeEventPublisher likeEventPublisher;
    public void createArticleLike(Like like) {
        likeChecker.checkGatheringArticleLikeToAppend(like);
        Integer communityId = likeAppender.appendGatheringArticleLike(like);
        likeEventPublisher.publishLikeEvent(communityId);
    }
    public void deleteArticleLike(Like like) {
        likeChecker.checkGatheringArticleLikeToRemove(like);
        Integer communityId = likeRemover.removeGatheringArticleLike(like);
        likeEventPublisher.publishDisLikeEvent(communityId);
    }
}
