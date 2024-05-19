package org.v1;

import org.springframework.stereotype.Repository;
import org.v1.model.community.Community;
import org.v1.model.content.Contents;
import org.v1.model.content.GroupArticle;
import org.v1.model.content.PhotoArticle;
import org.v1.repository.ContentRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ContentJpaEntityRepository implements ContentRepository {
    @Override
    public Contents.PhotoArticles readPopularPhotoContent(Community community) {
        List<PhotoArticle> popularPhotoArticle = new ArrayList<>();
        return new Contents.PhotoArticles(popularPhotoArticle,popularPhotoArticle);
    }


    @Override
    public Contents.GroupArticles readPopularGroupContent(Community community) {
        List<GroupArticle> groupArticles = new ArrayList<>();
        return new Contents.GroupArticles(groupArticles,groupArticles);
    }

    @Override
    public void updatePhotoArticle(Contents.PhotoArticles photos) {
    }

}
