package org.v1;

import org.springframework.stereotype.Repository;
import org.v1.model.community.Community;
import org.v1.model.content.Contents;
import org.v1.model.content.GroupArticle;
import org.v1.model.content.PhotoArticle;
import org.v1.model.user.User;
import org.v1.repository.ContentRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContentJpaEntityRepository implements ContentRepository {
    @Override
    public Contents.PhotoArticles readPopularPhotoContent(Community community) {
        Community community1 = Community.withId(1,"축구","123");
        PhotoArticle article = PhotoArticle.withId(1L, LocalDateTime.now(), User.onlyUserId(1L),5,5,"http://",community1);
        List<PhotoArticle> popularPhotoArticle =List.of(article);
        return new Contents.PhotoArticles(popularPhotoArticle,popularPhotoArticle);
    }


    @Override
    public Contents.GroupArticles readPopularGroupContent(Community community) {
        Community community1 = Community.withId(1,"축구","123");
        User user = new User(1L,"음","http://");
        GroupArticle.withId(1L,LocalDateTime.now(),user,5,5,5,"123",community1);
        List<GroupArticle> groupArticles = new ArrayList<>();
        return new Contents.GroupArticles(groupArticles,groupArticles);
    }

    @Override
    public void updatePhotoArticle(Contents.PhotoArticles photos) {
    }

}
