package org.v1.implementaion.article;

import org.springframework.stereotype.Component;
import org.v1.model.article.PhotoArticle;
import org.v1.model.user.UserStatus;

@Component
public class PhotoArticleUpdater {
    public PhotoArticle updateUserArticleRelation(PhotoArticle article, boolean isUserArticle, boolean isUserLike) {
        return article.updateUserStatus(new UserStatus(isUserArticle, isUserLike));
    }
}
