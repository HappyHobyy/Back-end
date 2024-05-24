package org.v1.jpaentityrepository.photo;

import org.springframework.stereotype.Repository;
import org.v1.model.article.PhotoArticle;
import org.v1.model.article.PhotoAriticleContent;
import org.v1.model.imageVideo.ImageVideo;
import org.v1.model.user.User;
import org.v1.repository.article.PhotoArticleRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PhotoArticleJpaEntityRepository implements PhotoArticleRepository {

    @Override
    public List<PhotoArticle> readArticleLatest(int index,Integer communityId) {
        List<PhotoArticle> lst = new ArrayList<>();
        PhotoAriticleContent content = new PhotoAriticleContent("배고파", new ArrayList<>());
        PhotoArticle photoArticle = PhotoArticle.withId(1L, User.withId(1L, "감자", null), 1, LocalDateTime.now(), content, new PhotoArticle.LikesComments(5, 5));
        lst.add(photoArticle);
        return lst;
    }

    @Override
    public List<PhotoArticle> readArticleLikes(int index,Integer communityId) {
        List<PhotoArticle> lst = new ArrayList<>();
        PhotoAriticleContent content = new PhotoAriticleContent("배고파", new ArrayList<>());
        PhotoArticle photoArticle = PhotoArticle.withId(1L, User.withId(1L, "감자", null), 1, LocalDateTime.now(), content, new PhotoArticle.LikesComments(5, 5));
        lst.add(photoArticle);
        return lst;
    }

    @Override
    public long appendArticle(PhotoArticle photoArticle) {
        return 1L;
    }

    @Override
    public List<PhotoArticle> readPopularCommunityArticle(Integer communityId) {
        List<PhotoArticle> lst = new ArrayList<>();
        PhotoAriticleContent content = new PhotoAriticleContent("배고파", new ArrayList<>());
        PhotoArticle photoArticle = PhotoArticle.withId(1L, User.withId(1L, "감자", null), 1, LocalDateTime.now(), content, new PhotoArticle.LikesComments(5, 5));
        lst.add(photoArticle);
        return lst;
    }

    @Override
    public List<PhotoArticle> readNotPopularCommunityArticle(Integer communityId) {
        List<PhotoArticle> lst = new ArrayList<>();
        PhotoAriticleContent content = new PhotoAriticleContent("배고파", new ArrayList<>());
        PhotoArticle photoArticle = PhotoArticle.withId(1L, User.withId(1L, "감자", null), 1, LocalDateTime.now(), content, new PhotoArticle.LikesComments(5, 5));
        lst.add(photoArticle);
        return lst;
    }

    @Override
    public void appendArticleContent(List<ImageVideo> imageVideoList, Long articleId) {

    }

    @Override
    public void removeArticle(Long photoArticleId) {

    }

    @Override
    public PhotoAriticleContent readContent(Long articleId) {
        return new PhotoAriticleContent("배고파", new ArrayList<>());
    }
}
