package org.v1.service.photo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementaion.like.LikeAppender;
import org.v1.implementaion.like.LikeChecker;
import org.v1.implementaion.like.LikeEventPublisher;
import org.v1.implementaion.like.LikeRemover;
import org.v1.model.like.Like;
@Service
@AllArgsConstructor
public class PhotoArticleLikeService {
    private final LikeAppender likeAppender;
    private final LikeChecker likeChecker;
    private final LikeRemover likeRemover;
    private final LikeEventPublisher likeEventPublisher;
    public void createArticleLike(Like like) {
        likeChecker.checkPhotoArticleLikeToAppend(like);
        Integer communityId = likeAppender.appendPhotoArticleLike(like);
        likeEventPublisher.publishLikeEvent(communityId);
    }
    public void deleteArticleLike(Like like) {
        likeChecker.checkPhotoArticleLikeToRemove(like);
        Integer communityId = likeRemover.removePhotoArticleLike(like);
        likeEventPublisher.publishDisLikeEvent(communityId);
    }
}
