package org.v1.implementaion.like;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.repository.gathering.GatheringLikeRepository;
import org.v1.repository.photo.PhotoLikeRepository;

@Component
@AllArgsConstructor
public class LikeCounter {
    private final PhotoLikeRepository photoLikeRepository;
    private final GatheringLikeRepository gatheringLikeRepository;
    public Integer countPhotoArticleLikes(Long articleId) {
        return photoLikeRepository.countLike(articleId);
    }
    public Integer countGatheringArticleLikes(Long articleId){
        return gatheringLikeRepository.countLike(articleId);
    }
}
