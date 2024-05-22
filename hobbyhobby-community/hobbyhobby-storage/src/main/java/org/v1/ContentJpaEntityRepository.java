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
        User user = new User(1L, "음", "https://hobbyhobby.s3.ap-northeast-2.amazonaws.com/gathering/default_gathering_image.png");
        Community community1 = Community.withId(1, "축구");
        PhotoArticle article = PhotoArticle.withId(1L, LocalDateTime.now(), user, 5, 5, "https://hobbyhobby.s3.ap-northeast-2.amazonaws.com/gathering/default_gathering_image.png", community1);
        List<PhotoArticle> popularPhotoArticle = List.of(article);
        return new Contents.PhotoArticles(popularPhotoArticle, popularPhotoArticle);
    }

    @Override
    public Contents.GroupArticles readPopularGroupContent(Community community) {
        Community community1 = Community.withId(1, "축구");
        Community community2 = Community.withId(2, "야구");
        User user = new User(1L, "음", "https://hobbyhobby.s3.ap-northeast-2.amazonaws.com/gathering/default_gathering_image.png");
        List<Community> communities= new ArrayList<>();
        communities.add(community1);
        communities.add(community2);
        GroupArticle article = GroupArticle.withId(1L, LocalDateTime.now(), user, 5, 5, "https://hobbyhobby.s3.ap-northeast-2.amazonaws.com/gathering/default_gathering_image.png", communities);
        List<GroupArticle> groupArticles = List.of(article);
        return new Contents.GroupArticles(groupArticles, groupArticles);
    }

    @Override
    public void updatePhotoArticle(Contents.PhotoArticles photos) {
    }
}
