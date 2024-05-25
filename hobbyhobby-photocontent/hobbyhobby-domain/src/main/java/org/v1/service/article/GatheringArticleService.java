package org.v1.service.article;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.v1.implementaion.article.GatheringArticleAppender;
import org.v1.implementaion.article.GatheringArticleChecker;
import org.v1.implementaion.article.GatheringArticleReader;
import org.v1.implementaion.article.GatheringArticleRemover;
import org.v1.implementaion.imagevideo.ImageVideoManager;
import org.v1.implementaion.like.LikeChecker;
import org.v1.model.article.*;
import org.v1.model.imageVideo.ImageVideo;
import org.v1.model.like.Like;
import org.v1.model.user.UserStatus;

import java.util.List;

@Service
@AllArgsConstructor
public class GatheringArticleService {
    private final GatheringArticleReader articleReader;
    private final GatheringArticleAppender articleAppender;
    private final GatheringArticleRemover articleRemover;
    private final GatheringArticleChecker articleChecker;
    private final ImageVideoManager imageVideoManager;
    private final LikeChecker likeChecker;

    public List<GatheringArticle> getTenArticleLatest(Integer index, ArticleType type) {
        return articleReader.readLatestArticles(index, type);
    }

    public List<GatheringArticle> getTenArticleSearch(Integer index, GatheringInfo info) {
        return articleReader.readSearchArticles(index, info);
    }

    public Long createArticle(GatheringArticle article, GatheringArticleContent content) {
        Long articleId = articleAppender.appendArticle(article, content);
        ImageVideo image = imageVideoManager.appendFile(articleId, article.getImageVideo());
        articleAppender.appendArticleImage(image, article.getInfo().initArticleId(articleId));
        return articleId;
    }

    public void deleteArticle(GatheringInfo info) {
        imageVideoManager.removeImage(articleReader.readImage(info));
        articleRemover.removeArticle(info);
    }

    @Transactional
    public GatheringArticleDetail getArticleDetail(GatheringInfo info, Long userId) {
        boolean isUserLiked = likeChecker.checkArticleLiked(new Like(userId, info.articleId(), info.type()));
        boolean isUserOwner = articleChecker.isArticleUserOwner(userId, info);
        boolean isUserJoined = articleChecker.isArticleUserJoined(userId, info);
        GatheringArticleContent content = articleReader.readContent(info);
        return new GatheringArticleDetail(content, new UserStatus(isUserLiked, isUserOwner, isUserJoined));
    }
}
