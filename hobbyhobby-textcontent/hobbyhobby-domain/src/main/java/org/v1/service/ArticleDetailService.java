package org.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementaion.ArticleDetailAppender;
import org.v1.implementaion.ArticleDetailChecker;
import org.v1.implementaion.ArticleDetailRemover;
import org.v1.model.ArticleDetail;
import org.v1.implementaion.ArticleDetailReader;
import org.v1.model.Comment;
import org.v1.model.Like;

@Service
@AllArgsConstructor
public class ArticleDetailService {
    private final ArticleDetailReader articleDetailReader;
    private final ArticleDetailAppender articleDetailAppender;
    private final ArticleDetailRemover articleDetailRemover;
    private final ArticleDetailChecker articleDetailChecker;
    public ArticleDetail getArticleDetail(Long articleId,Long userId) {
        ArticleDetail.UserStatus userStatus = articleDetailChecker.checkArticleUserRelation(articleId,userId);
        return articleDetailReader.read(articleId).changeUserStatus(userStatus);
    }
    public void createArticleLike(Like like) {
        articleDetailChecker.checkArticleLikeToAppend(like);
        articleDetailAppender.appendLike(like);
    }
    public void createArticleComment(Comment comment,Long articleId) {
        articleDetailChecker.checkArticleExist(articleId);
        articleDetailAppender.appendComment(comment,articleId);
    }
    public void deleteArticleLike(Like like) {
        articleDetailChecker.checkArticleLikeToRemove(like);
        articleDetailRemover.removeLike(like);
    }
    public void deleteArticleComment(Long CommentId) {
        articleDetailRemover.removeComment(CommentId);
    }
}
