package org.v1.service.like;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementaion.like.LikeAppender;
import org.v1.implementaion.like.LikeChecker;
import org.v1.implementaion.like.LikeEventPublisher;
import org.v1.implementaion.like.LikeRemover;
import org.v1.model.like.Like;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticleLikeService {
    private final LikeAppender likeAppender;
    private final LikeChecker likeChecker;
    private final LikeRemover likeRemover;
    private final LikeEventPublisher likeEventPublisher;
    public void createArticleLike(Like like) {
        likeChecker.checkArticleLikeToAppend(like);
        List<Integer> communityIds = likeAppender.appendArticleLike(like);
        communityIds.forEach(communityId ->
                likeEventPublisher.publishLikeEvent(communityId, like.type())
        );
    }
    public void deleteArticleLike(Like like) {
        likeChecker.checkArticleLikeToRemove(like);
        List<Integer> communityIds = likeRemover.removeArticleLike(like);
        communityIds.forEach(communityId ->
                likeEventPublisher.publishDisLikeEvent(communityId, like.type())
        );
    }
}
