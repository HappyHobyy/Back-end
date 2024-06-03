package org.v1.jpaentityrepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.v1.jpaentity.HotPhotoContentJpaEntity;
import org.v1.jpaentity.HotUnionGatheringJpaEntity;
import org.v1.jparepository.HotPhotoContentJpaRepository;
import org.v1.jparepository.HotUnionGatheringJpaRepository;
import org.v1.model.community.Community;
import org.v1.model.content.Contents;
import org.v1.model.content.GatheringArticle;
import org.v1.model.content.PhotoArticle;
import org.v1.model.user.User;
import org.v1.repository.ContentRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class ContentJpaEntityRepository implements ContentRepository {
    private final HotPhotoContentJpaRepository hotPhotoContentJpaRepository;
    private final HotUnionGatheringJpaRepository hotUnionGatheringJpaRepository;
    @Override
    public Contents.PhotoArticles readPopularPhotoContent() {
        List<PhotoArticle> notPopularPhotoArticle = mapToPhotoArticle(hotPhotoContentJpaRepository.findNotPopularWithCommunity());
        List<PhotoArticle> PopularPhotoArticle = mapToPhotoArticle(hotPhotoContentJpaRepository.findPopularWithCommunity());
        return new Contents.PhotoArticles(notPopularPhotoArticle, PopularPhotoArticle);
    }

    @Override
    public Contents.GatheringArticles readPopularGatheringContent() {
        List<GatheringArticle> notPopularPhotoArticle = mapToGatheringArticle(hotUnionGatheringJpaRepository.findNotPopularWithCommunity());
        List<GatheringArticle> PopularPhotoArticle = mapToGatheringArticle(hotUnionGatheringJpaRepository.findPopularWithCommunity());
        return new Contents.GatheringArticles(notPopularPhotoArticle, PopularPhotoArticle);
    }

    @Override
    public void updatePhotoArticle(Contents.PhotoArticles photos) {

    }

    @Override
    public void updateGatheringArticle(Contents.GatheringArticles photos) {

    }
    private List<PhotoArticle> mapToPhotoArticle(List<HotPhotoContentJpaEntity> photos) {
        return photos.stream()
                .map(HotPhotoContentJpaEntity::to)
                .collect(Collectors.toList());
    }

    private List<GatheringArticle> mapToGatheringArticle(List<HotUnionGatheringJpaEntity> unions) {
        return unions.stream()
                .map(HotUnionGatheringJpaEntity::to)
                .collect(Collectors.toList());
    }
}
