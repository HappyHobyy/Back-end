package org.v1.jpaentityrepository.photo;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.v1.jpaentity.photo.PhotoArticleJpaEntity;
import org.v1.jpaentity.photo.PhotoImageJpaEntity;
import org.v1.jparepository.photo.PhotoArticleJpaRepository;
import org.v1.jparepository.photo.PhotoImageJpaRepository;
import org.v1.model.article.PhotoArticle;
import org.v1.model.article.PhotoArticleContent;
import org.v1.model.imageVideo.ImageVideo;
import org.v1.model.user.User;
import org.v1.repository.article.PhotoArticleRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class PhotoArticleJpaEntityRepository implements PhotoArticleRepository {
    private final PhotoArticleJpaRepository photoArticleJpaRepository;
    private final PhotoImageJpaRepository photoImageJpaRepository;

    @Override
    public List<PhotoArticle> readArticleLatest(int index, Integer communityId) {
        Pageable pageable = PageRequest.of(index, 10);
        Page<PhotoArticleJpaEntity> page = photoArticleJpaRepository.findAllByCommunityIdOrderByCreatedAtDesc(pageable,communityId);
        return mapPhotoArticles(page);
    }

    @Override
    public List<PhotoArticle> readArticleLikes(int index, Integer communityId) {
        Pageable pageable = PageRequest.of(index, 10);
        Page<PhotoArticleJpaEntity> page = photoArticleJpaRepository.findAllByCommunityIdOrderByLikesDesc(pageable,communityId);
        return mapPhotoArticles(page);
    }

    @Override
    public long appendArticle(PhotoArticle photoArticle) {
        PhotoArticleJpaEntity entity = PhotoArticleJpaEntity.of(photoArticle);
        PhotoArticleJpaEntity jpaEntity = photoArticleJpaRepository.save(entity);
        return jpaEntity.getId();
    }

    @Override
    public List<PhotoArticle> readPopularCommunityArticle(Integer communityId) {
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        Instant startInstant = startDate.atZone(ZoneId.of("Asia/Seoul")).toInstant();
        Pageable pageable = PageRequest.of(0, 10);
        Page<PhotoArticleJpaEntity> page = photoArticleJpaRepository.findAllByCommunityIdAndCreatedAtAfterOrderByLikesDesc(communityId.longValue(), startInstant, pageable);
        return mapPhotoArticles(page);
    }

    @Override
    public List<PhotoArticle> readNotPopularCommunityArticle(Integer communityId) {
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        Instant startInstant = startDate.atZone(ZoneId.of("Asia/Seoul")).toInstant();
        Pageable pageable = PageRequest.of(0, 10);
        Page<PhotoArticleJpaEntity> page = photoArticleJpaRepository.findAllByCommunityIdNotAndCreatedAtAfterOrderByLikesDesc(communityId.longValue(), startInstant, pageable);
        return mapPhotoArticles(page);
    }
    @Override
    public void appendArticleContent(List<ImageVideo> imageVideoList, Long articleId) {
        imageVideoList.forEach(imageVideo ->
                photoImageJpaRepository.save(PhotoImageJpaEntity.of(imageVideo, articleId))
        );
    }

    @Override
    public void removeArticle(Long photoArticleId) {
        photoArticleJpaRepository.deleteById(photoArticleId);
    }

    @Override
    public List<ImageVideo> readImageList(Long articleId) {
        List<PhotoImageJpaEntity> photoImageListJpaEntities = photoImageJpaRepository.findAllByPhotoContent_Id(articleId);
        if (photoImageListJpaEntities != null) {
            return photoImageListJpaEntities.stream().map(PhotoImageJpaEntity::toImageVideo).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }


    private List<PhotoArticle> mapPhotoArticles(Page<PhotoArticleJpaEntity> page) {
        if (page != null && page.hasContent()) {
            return page.getContent().stream().map(this::mapPhotoArticle).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    private PhotoArticle mapPhotoArticle(PhotoArticleJpaEntity photoArticleJpaEntity) {
        PhotoArticle photoArticle = photoArticleJpaEntity.to();
        List<ImageVideo> imageVideos = readImageList(photoArticle.getId());
        return photoArticle.updateImageList(imageVideos);
    }
}
