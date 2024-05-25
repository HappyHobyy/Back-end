package org.v1.jpaentityrepository.like;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.v1.jpaentity.gathering.GatheringJpaEntity;
import org.v1.jpaentity.gathering.LikedGatheringJpaEntity;
import org.v1.jpaentity.gathering.LikedUnionGatheringJpaEntity;
import org.v1.jpaentity.gathering.UnionGatheringJpaEntity;
import org.v1.jpaentity.photo.LikedPhotoJpaEntity;
import org.v1.jpaentity.photo.PhotoArticleJpaEntity;
import org.v1.jpaentity.user.UserJpaEntity;
import org.v1.jparepository.gathering.*;
import org.v1.jparepository.photo.LikedPhotoJpaRepository;
import org.v1.jparepository.photo.PhotoArticleJpaRepository;
import org.v1.model.like.Like;
import org.v1.repository.like.LikeRepository;

import java.util.List;

@Repository
@AllArgsConstructor
public class LikeJpaEntityRepository implements LikeRepository {
    private final LikedPhotoJpaRepository likedPhotoJpaRepository;
    private final LikedGatheringJpaRepository likedGatheringJpaRepository;
    private final LikedUnionGatheringJpaRepository likedUnionGatheringJpaRepository;
    private final PhotoArticleJpaRepository photoArticleJpaRepository;
    private final UnionGatheringJpaRepository unionGatheringJpaRepository;
    private final UnionGatheringDetailJpaRepository unionGatheringDetailJpaRepository;
    private final GatheringJpaRepository gatheringJpaRepository;
    private final GatheringDetailJpaRepository gatheringDetailJpaRepository;

    @Override
    public boolean checkArticleLike(Like like) {
        return switch (like.type()) {
            case UNION_GATHERING ->
                    likedUnionGatheringJpaRepository.existsLikedUnionGatheringJpaEntityByUnionGathering_Id(like.articleId());
            case SINGLE_GATHERING ->
                    likedGatheringJpaRepository.existsLikedGatheringJpaEntityByGathering_Id(like.articleId());
            case H_LOG -> likedPhotoJpaRepository.existsLikedPhotoJpaEntityByPhotoContent_Id(like.articleId());
        };
    }

    @Override
    public List<Integer> appendLike(Like like) {
        return switch (like.type()) {
            case UNION_GATHERING -> {
                LikedUnionGatheringJpaEntity entity = LikedUnionGatheringJpaEntity.of(like);
                likedUnionGatheringJpaRepository.save(entity);
                UnionGatheringJpaEntity unionGatheringJpaEntity = unionGatheringJpaRepository.findWithCommunityById(like.articleId());
                unionGatheringDetailJpaRepository.incrementLikesById(like.articleId());
                yield List.of(unionGatheringJpaEntity.getCommunity1().getId().intValue(), unionGatheringJpaEntity.getCommunity2().getId().intValue());
            }
            case SINGLE_GATHERING -> {
                LikedGatheringJpaEntity entity = LikedGatheringJpaEntity.of(like);
                likedGatheringJpaRepository.save(entity);
                Long community = gatheringJpaRepository.findWithCommunityById(like.articleId()).getCommunity().getId();
                gatheringDetailJpaRepository.incrementLikesById(like.articleId());
                yield List.of(community.intValue());
            }
            case H_LOG -> {
                LikedPhotoJpaEntity entity = LikedPhotoJpaEntity.of(like);
                likedPhotoJpaRepository.save(entity);
                photoArticleJpaRepository.incrementLikesById(like.articleId());
                PhotoArticleJpaEntity article = photoArticleJpaRepository.findWithCommunityById(like.articleId());
                yield List.of(article.getCommunity().getId().intValue());
            }
        };
    }

    @Override
    public List<Integer> removeLike(Like like) {
        return switch (like.type()) {
            case UNION_GATHERING -> {
                UnionGatheringJpaEntity unionGatheringJpaEntity = unionGatheringJpaRepository.findWithCommunityById(like.articleId());
                unionGatheringDetailJpaRepository.decrementLikesById(like.articleId());
                likedUnionGatheringJpaRepository.deleteByUnionGathering_IdAndUser_Id(like.articleId(),like.userId());
                yield List.of(unionGatheringJpaEntity.getCommunity1().getId().intValue(), unionGatheringJpaEntity.getCommunity2().getId().intValue());
            }
            case SINGLE_GATHERING -> {
                Long community = gatheringJpaRepository.findWithCommunityById(like.articleId()).getCommunity().getId();
                likedGatheringJpaRepository.deleteByGathering_IdAndUser_Id(like.articleId(),like.userId());
                gatheringDetailJpaRepository.decrementLikesById(like.articleId());
                yield List.of(community.intValue());
            }
            case H_LOG -> {
                PhotoArticleJpaEntity article = photoArticleJpaRepository.findWithCommunityById(like.articleId());
                likedPhotoJpaRepository.deleteByPhotoContent_IdAndUser_Id(like.articleId(),like.userId());
                photoArticleJpaRepository.decrementLikesById(like.articleId());
                yield List.of(article.getCommunity().getId().intValue());
            }
        };
    }
}
