package org.v1.jparepository.gathering;

import org.springframework.stereotype.Repository;
import org.v1.model.article.*;
import org.v1.model.imageVideo.ImageVideo;
import org.v1.model.user.User;
import org.v1.model.user.UserStatus;
import org.v1.repository.article.GatheringArticleRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GatheringArticleJpaEntityRepository implements GatheringArticleRepository {

    @Override
    public List<GatheringArticle> readArticleLatest(ArticleType type) {
        GatheringInfo info;
        List<GatheringArticle> lst =  new ArrayList<>();
        switch (type) {
            case MULTI_GATHERING:
                info = GatheringInfo.multiGatheringWithCommunity(List.of(1, 2));
                break;
            case SINGLE_GATHERING:
                info = GatheringInfo.singleGatheringWithCommunity(1);
                break;
            default:
                return null;
        }
        User testUser = User.withId(1L,"감자",null);
        GatheringArticle article = GatheringArticle.withId(
                1L,
                "모임1",
                testUser,
                info,
                LocalDateTime.now(),
                5,
                10,
                ImageVideo.withOnlyPath("sampleVideo.mp4")
        );
        lst.add(article);
        return lst;
    }

    @Override
    public List<GatheringArticle> readArticleSearch(GatheringInfo info) {
        List<GatheringArticle> lst =  new ArrayList<>();
        User testUser = User.withId(1L,"감자",null);
        GatheringArticle article = GatheringArticle.withId(
                1L,
                "모임1",
                testUser,
                info,
                LocalDateTime.now(),
                5,
                10,
                ImageVideo.withOnlyPath("sampleVideo.mp4")
        );
        lst.add(article);
        return lst;
    }

    @Override
    public List<GatheringArticle> readPopularCommunityArticle(Integer communityId) {
        GatheringInfo info;
        List<GatheringArticle> lst =  new ArrayList<>();
        info = GatheringInfo.multiGatheringWithCommunity(List.of(1, 2));
        User testUser = User.withId(1L,"감자",null);
        GatheringArticle article = GatheringArticle.withId(
                1L,
                "모임1",
                testUser,
                info,
                LocalDateTime.now(),
                5,
                10,
                ImageVideo.withOnlyPath("sampleVideo.mp4")
        );
        lst.add(article);
        return lst;
    }

    @Override
    public List<GatheringArticle> readNotPopularCommunityArticle(Integer communityId) {
        GatheringInfo info;
        List<GatheringArticle> lst =  new ArrayList<>();
        info = GatheringInfo.multiGatheringWithCommunity(List.of(1, 2));
        User testUser = User.withId(1L,"감자",null);
        GatheringArticle article = GatheringArticle.withId(
                1L,
                "모임1",
                testUser,
                info,
                LocalDateTime.now(),
                5,
                10,
                ImageVideo.withOnlyPath("sampleVideo.mp4")
        );
        lst.add(article);
        return lst;
    }

    @Override
    public Long appendArticle(GatheringArticle photoArticle) {
        return null;
    }

    @Override
    public void appendArticleContent(GatheringArticleContent content, GatheringInfo info) {

    }

    @Override
    public void removeArticle(GatheringInfo info) {

    }

    @Override
    public boolean isArticleUserOwner(GatheringInfo info, Long userId) {
        return true;
    }

    @Override
    public boolean isArticleUserJoined(GatheringInfo info, Long userId) {
        return false;
    }

    @Override
    public GatheringArticleContent readContent(GatheringInfo info) {
        return new GatheringArticleContent("123","공원",LocalDateTime.now(),ImageVideo.withOnlyPath("123.png"));
    }
}
