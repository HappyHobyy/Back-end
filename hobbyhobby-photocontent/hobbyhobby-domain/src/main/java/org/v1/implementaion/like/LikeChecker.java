package org.v1.implementaion.like;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.error.BusinessException;
import org.v1.error.ErrorCode;
import org.v1.model.like.Like;
import org.v1.repository.gathering.GatheringLikeRepository;
import org.v1.repository.photo.PhotoLikeRepository;

@Component
@AllArgsConstructor
public class LikeChecker {
    private final PhotoLikeRepository photoLikeRepository;
    private final GatheringLikeRepository gatheringLikeRepository;
    public void checkPhotoArticleLikeToAppend(Like like) {
        if(photoLikeRepository.checkArticleLike(like)){
            throw new BusinessException(ErrorCode.PHOTO_ALREADY_LIKE);
        };
    }
    public void checkPhotoArticleLikeToRemove(Like like) {
        if(!photoLikeRepository.checkArticleLike(like)){
            throw new BusinessException(ErrorCode.PHOTO_ALREADY_DISLIKE);
        };
    }
    public void checkGatheringArticleLikeToAppend(Like like) {
        if(gatheringLikeRepository.checkArticleLike(like)){
            throw new BusinessException(ErrorCode.PHOTO_ALREADY_LIKE);
        };
    }
    public void checkGatheringArticleLikeToRemove(Like like) {
        if(!gatheringLikeRepository.checkArticleLike(like)){
            throw new BusinessException(ErrorCode.PHOTO_ALREADY_DISLIKE);
        };
    }
}
