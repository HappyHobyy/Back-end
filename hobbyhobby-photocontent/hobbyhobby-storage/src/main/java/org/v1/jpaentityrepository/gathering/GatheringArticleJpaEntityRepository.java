package org.v1.jpaentityrepository.gathering;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.v1.jpaentity.gathering.GatheringDetailJpaEntity;
import org.v1.jpaentity.gathering.GatheringJpaEntity;
import org.v1.jpaentity.gathering.UnionGatheringDetailJpaEntity;
import org.v1.jpaentity.gathering.UnionGatheringJpaEntity;
import org.v1.jparepository.gathering.*;
import org.v1.model.article.*;
import org.v1.model.group.GatheringMember;
import org.v1.model.imageVideo.ImageVideo;
import org.v1.model.user.User;
import org.v1.repository.article.GatheringArticleRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class GatheringArticleJpaEntityRepository implements GatheringArticleRepository {
    private final UnionGatheringJpaRepository unionGatheringJpaRepository;
    private final JoinedGatheringJpaRepository joinedGatheringJpaRepository;
    private final JoinedUnionGatheringJpaRepository joinedUnionGatheringJpaRepository;
    private final GatheringDetailJpaRepository gatheringDetailJpaRepository;
    private final UnionGatheringDetailJpaRepository unionGatheringDetailJpaRepository;
    private final GatheringJpaRepository gatheringJpaRepository;

    @Override
    public List<GatheringArticle> readArticleLatest(Integer index, ArticleType type) {
        Pageable pageable = PageRequest.of(index, 10);
        return switch (type) {
            case UNION_GATHERING -> {
                Page<UnionGatheringDetailJpaEntity> unionEntities =
                        unionGatheringDetailJpaRepository.findAllByCommunityIdOrderByDesc(pageable);
                yield unionEntities.isEmpty() ? new ArrayList<>() :
                        unionEntities.stream()
                                .map(UnionGatheringDetailJpaEntity::toGatheringArticle)
                                .collect(Collectors.toList());
            }
            case SINGLE_GATHERING -> {
                Page<GatheringDetailJpaEntity> entities =
                        gatheringDetailJpaRepository.findAllByCommunityIdOrderByDesc(pageable);
                yield entities.isEmpty() ? new ArrayList<>() :
                        entities.stream()
                                .map(GatheringDetailJpaEntity::toGatheringArticle)
                                .collect(Collectors.toList());
            }
            default -> new ArrayList<>();
        };
    }

    @Override
    public List<GatheringArticle> readArticleSearch(Integer index, GatheringInfo info) {
        Pageable pageable = PageRequest.of(index, 10);
        return switch (info.type()) {
            case UNION_GATHERING -> {
                Page<UnionGatheringDetailJpaEntity> unionEntities;
                if (info.communityIds().size() == 1) {
                    unionEntities = unionGatheringDetailJpaRepository.findAllByCommunityIdOrderByDescOne(info.communityIds().get(0).longValue(), pageable);
                } else {
                    unionEntities = unionGatheringDetailJpaRepository.findAllByCommunityIdOrderByDescTwo(info.communityIds().get(0).longValue(), info.communityIds().get(1).longValue(), pageable);
                }
                yield unionEntities.isEmpty() ? new ArrayList<>() :
                        unionEntities.stream()
                                .map(UnionGatheringDetailJpaEntity::toGatheringArticle)
                                .collect(Collectors.toList());
            }
            case SINGLE_GATHERING -> {
                Page<GatheringDetailJpaEntity> entities =
                        gatheringDetailJpaRepository.findAllByCommunityIdAndCreatedAtAfterOrderByDesc(info.communityIds().get(0).longValue(), pageable);
                yield entities.isEmpty() ? new ArrayList<>() :
                        entities.stream()
                                .map(GatheringDetailJpaEntity::toGatheringArticle)
                                .collect(Collectors.toList());
            }
            default -> new ArrayList<>();
        };
    }

    @Override
    public List<GatheringArticle> readPopularCommunityArticle(Integer communityId) {
        Pageable pageable = PageRequest.of(0, 10);
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        Instant startInstant = startDate.atZone(ZoneId.of("Asia/Seoul")).toInstant();
        Page<UnionGatheringDetailJpaEntity> unionEntities =
                unionGatheringDetailJpaRepository.findAllByCommunityIdAndCreatedAtAfterOrderByLikesDesc(
                        communityId.longValue(),
                        startInstant, pageable);
        return unionEntities.isEmpty() ? new ArrayList<>() :
                unionEntities.stream()
                        .map(UnionGatheringDetailJpaEntity::toGatheringArticle)
                        .collect(Collectors.toList());
    }

    @Override
    public List<GatheringArticle> readNotPopularCommunityArticle(Integer communityId) {
        Pageable pageable = PageRequest.of(0, 10);
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        Instant startInstant = startDate.atZone(ZoneId.of("Asia/Seoul")).toInstant();
        Page<UnionGatheringDetailJpaEntity> entities =
                unionGatheringDetailJpaRepository.findAllByCommunityIdNotAndCreatedAtAfterOrderByLikesDesc(
                        communityId.longValue(),
                        startInstant, pageable);
        return entities.isEmpty() ? new ArrayList<>() :
                entities.stream()
                        .map(UnionGatheringDetailJpaEntity::toGatheringArticle)
                        .collect(Collectors.toList());
    }

    @Override
    public Long appendArticle(GatheringArticle article, GatheringArticleContent content) {
        switch (article.getInfo().type()) {
            case UNION_GATHERING:
                UnionGatheringJpaEntity unionGatheringJpaEntity = UnionGatheringJpaEntity.of(article);
                UnionGatheringDetailJpaEntity unionGatheringDetailJpaEntity = UnionGatheringDetailJpaEntity.of(content, unionGatheringJpaEntity);
                unionGatheringDetailJpaRepository.save(unionGatheringDetailJpaEntity);
                return unionGatheringJpaEntity.getId();
            case SINGLE_GATHERING:
                GatheringJpaEntity gatheringJpaEntity = GatheringJpaEntity.of(article);
                GatheringDetailJpaEntity gatheringDetailJpaEntity = GatheringDetailJpaEntity.of(content, gatheringJpaEntity);
                gatheringDetailJpaRepository.save(gatheringDetailJpaEntity);
                return gatheringJpaEntity.getId();
        }
        return null;
    }

    @Override
    public void appendArticleImage(ImageVideo imageVideo, GatheringInfo info) {
        switch (info.type()) {
            case UNION_GATHERING:
                unionGatheringJpaRepository.updateImagePath(info.articleId(), imageVideo.path());
                break;
            case SINGLE_GATHERING:
                gatheringJpaRepository.updateImagePath(info.articleId(), imageVideo.path());
                break;
        }
    }

    @Override
    public void removeArticle(GatheringInfo info) {
        switch (info.type()) {
            case UNION_GATHERING:
                unionGatheringJpaRepository.deleteById(info.articleId());
                break;
            case SINGLE_GATHERING:
                gatheringJpaRepository.deleteById(info.articleId());
                break;
        }
    }

    @Override
    public boolean isArticleUserOwner(GatheringInfo info, Long userId) {
        return switch (info.type()) {
            case UNION_GATHERING -> {
                UnionGatheringJpaEntity unionGathering = unionGatheringJpaRepository.findById(info.articleId()).orElseThrow();
                yield unionGathering.getUser().getId().equals(userId);
            }
            case SINGLE_GATHERING -> {
                GatheringJpaEntity gathering = gatheringJpaRepository.findById(info.articleId()).orElseThrow();
                yield gathering.getUser().getId().equals(userId);
            }
            default -> throw new IllegalArgumentException("Invalid gathering type: " + info.type());
        };
    }

    @Override
    public boolean isArticleUserJoined(GatheringInfo info, Long userId) {
        return switch (info.type()) {
            case UNION_GATHERING ->
                    joinedUnionGatheringJpaRepository.existsByUnionGathering_IdAndUser_Id(info.articleId(), userId);
            case SINGLE_GATHERING ->
                    joinedGatheringJpaRepository.existsByGathering_IdAndUser_Id(info.articleId(), userId);
            default -> throw new IllegalArgumentException("Invalid gathering type: " + info.type());
        };
    }

    @Override
    public boolean checkGatheringMemberJoined(GatheringMember member) {
        return switch (member.type()) {
            case UNION_GATHERING ->
                    joinedUnionGatheringJpaRepository.existsByUnionGathering_IdAndUser_Id(member.articleId(), member.userId());
            case SINGLE_GATHERING ->
                    joinedGatheringJpaRepository.existsByGathering_IdAndUser_Id(member.articleId(), member.userId());
            default -> throw new IllegalArgumentException("Invalid gathering type: " + member.type());
        };
    }

    @Override
    public GatheringArticleContent readContent(GatheringInfo info) {
        return switch (info.type()) {
            case UNION_GATHERING:
                yield unionGatheringDetailJpaRepository.getByUnionGathering_Id(info.articleId()).toContent();
            case SINGLE_GATHERING:
                yield gatheringDetailJpaRepository.getByGathering_Id(info.articleId()).toContent();
            default:
                throw new IllegalArgumentException("Invalid gathering type: " + info.type());
        };
    }

    @Override
    public ImageVideo readImage(GatheringInfo info) {
        return switch (info.type()) {
            case UNION_GATHERING:
                yield unionGatheringJpaRepository.findById(info.articleId()).orElseThrow().toImageVideo();
            case SINGLE_GATHERING:
                yield gatheringJpaRepository.findById(info.articleId()).orElseThrow().toImageVideo();
            default:
                throw new IllegalArgumentException("Invalid gathering type: " + info.type());
        };
    }
}
