package org.v1.jparepository.gathering;

import org.springframework.stereotype.Repository;
import org.v1.model.like.Like;
import org.v1.repository.gathering.GatheringLikeRepository;
import org.v1.repository.photo.PhotoLikeRepository;

@Repository
public class GatheringLikeJpaEntityRepository implements GatheringLikeRepository {
    @Override
    public boolean checkArticleLike(Like like) {
        return false;
    }

    @Override
    public Integer appendLike(Like like) {
        return 1;
    }

    @Override
    public Integer removeLike(Like like) {
        return 1;
    }

    @Override
    public Integer countLike(Long articleId) {
        return null;
    }
}
