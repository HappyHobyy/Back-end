package org.v1.jparepository;

import org.springframework.stereotype.Repository;
import org.v1.model.Like;
import org.v1.repository.LikeRepository;
@Repository
public class TextLikeJpaEntityRepository implements LikeRepository {
    @Override
    public boolean checkArticleLike(Like like) {
        return false;
    }

    @Override
    public void appendLike(Like like) {

    }

    @Override
    public void removeLike(Like like) {

    }

    @Override
    public Integer countLike(Long articleId) {
        return null;
    }
}
