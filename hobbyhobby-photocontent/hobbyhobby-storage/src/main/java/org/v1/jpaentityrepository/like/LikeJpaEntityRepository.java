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
import org.v1.jparepository.*;
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
                LikedUnionGatheringJpaEntity entity = LikedUnionGatheringJpaEntity.builder()
                        .unionGathering(UnionGatheringJpaEntity.onlyWithId(like.articleId()))
                        .user(UserJpaEntity.onlyWithId(like.userId()))
                        .build();
                likedUnionGatheringJpaRepository.save(entity);
                Long community1 = unionGatheringJpaRepository.findWithCommunityById(like.articleId()).getCommunity1().getId();
                Long community2 = unionGatheringJpaRepository.findWithCommunityById(like.articleId()).getCommunity2().getId();
                unionGatheringDetailJpaRepository.incrementLikesById(like.articleId());
                yield List.of(community1.intValue(), community2.intValue());
            }
            case SINGLE_GATHERING -> {
                LikedGatheringJpaEntity entity = LikedGatheringJpaEntity.builder()
                        .gathering(GatheringJpaEntity.onlyWithId(like.articleId()))
                        .user(UserJpaEntity.onlyWithId(like.userId()))
                        .build();
                likedGatheringJpaRepository.save(entity);
                Long community = gatheringJpaRepository.findWithCommunityById(like.articleId()).getCommunity().getId();
                gatheringDetailJpaRepository.incrementLikesById(like.articleId());
                yield List.of(community.intValue());
            }
            case H_LOG -> {
                LikedPhotoJpaEntity entity = LikedPhotoJpaEntity.builder()
                        .photoContent(PhotoArticleJpaEntity.onlyWithId(like.articleId()))
                        .user(UserJpaEntity.onlyWithId(like.userId()))
                        .build();
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
                Long community1 = unionGatheringJpaRepository.findWithCommunityById(like.articleId()).getCommunity1().getId();
                Long community2 = unionGatheringJpaRepository.findWithCommunityById(like.articleId()).getCommunity2().getId();
                unionGatheringDetailJpaRepository.decrementLikesById(like.articleId());
                likedUnionGatheringJpaRepository.deleteById(like.articleId());
                yield List.of(community1.intValue(), community2.intValue());
            }
            case SINGLE_GATHERING -> {
                Long community = gatheringJpaRepository.findWithCommunityById(like.articleId()).getCommunity().getId();
                likedGatheringJpaRepository.deleteById(like.articleId());
                gatheringDetailJpaRepository.decrementLikesById(like.articleId());
                yield List.of(community.intValue());
            }
            case H_LOG -> {
                PhotoArticleJpaEntity article = photoArticleJpaRepository.findWithCommunityById(like.articleId());
                likedUnionGatheringJpaRepository.deleteById(like.articleId());
                photoArticleJpaRepository.decrementLikesById(like.articleId());
                yield List.of(article.getCommunity().getId().intValue());
            }
        };
    }
}
