package org.v1.implementaion.like;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.like.Like;
import org.v1.repository.gathering.GatheringLikeRepository;
import org.v1.repository.photo.PhotoLikeRepository;

@Component
@AllArgsConstructor
public class LikeRemover {
    private final PhotoLikeRepository photoLikeRepository;
    private final GatheringLikeRepository gatheringLikeRepository;
    public Integer removePhotoArticleLike(final Like like) {
        return photoLikeRepository.removeLike(like);
    }
    public Integer removeGatheringArticleLike(final Like like) {
        return gatheringLikeRepository.removeLike(like);
    }
}
