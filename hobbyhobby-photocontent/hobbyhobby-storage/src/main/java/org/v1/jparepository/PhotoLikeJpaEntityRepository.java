package org.v1.jparepository;

import org.springframework.stereotype.Repository;
import org.v1.model.like.Like;
import org.v1.repository.LikeRepository;

@Repository
public class PhotoLikeJpaEntityRepository implements LikeRepository {
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
