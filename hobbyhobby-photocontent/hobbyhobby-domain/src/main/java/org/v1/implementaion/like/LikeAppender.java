package org.v1.implementaion.like;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.like.Like;
import org.v1.repository.gathering.GatheringLikeRepository;
import org.v1.repository.photo.PhotoLikeRepository;

@Component
@AllArgsConstructor
public class LikeAppender {
    private final PhotoLikeRepository photoLikeRepository;
    private final GatheringLikeRepository gatheringLikeRepository;
    public Integer appendPhotoArticleLike(final Like like) {
        return photoLikeRepository.appendLike(like);
    }
    public Integer appendGatheringArticleLike(final Like like) {
        return gatheringLikeRepository.appendLike(like);
    }
}
