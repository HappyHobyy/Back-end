package org.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementaion.*;
import org.v1.model.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArticleDetailService {
    private final ArticleDetailReader articleDetailReader;
    private final ArticleDetailAppender articleDetailAppender;
    private final ArticleDetailRemover articleDetailRemover;
    private final ArticleDetailChecker articleDetailChecker;
    private final UserReader userReader;
    public ArticleDetail getArticleDetail(Long articleId,Long userId) {
        ArticleDetail.UserStatus userStatus = articleDetailChecker.checkArticleUserRelation(articleId,userId);
        List<Comment> comments = articleDetailReader.readComments(articleId);
        List<Comment> updatedComments = comments.stream()
                .map(comment -> {
                    User commentUser = userReader.readUser(comment.getUser().id());
                    return comment.changeUser(commentUser,userId);
                })
                .toList();
        Content content = articleDetailReader.readContent(articleId);
        return new ArticleDetail(updatedComments,content,userStatus);
    }
    public void createArticleLike(Like like) {
        articleDetailChecker.checkArticleLikeToAppend(like);
        articleDetailAppender.appendLike(like);
    }
    public Long createArticleComment(Comment comment,Long articleId) {
        articleDetailChecker.checkArticleExist(articleId);
        return articleDetailAppender.appendComment(comment,articleId);
    }
    public void deleteArticleLike(Like like) {
        articleDetailChecker.checkArticleLikeToRemove(like);
        articleDetailRemover.removeLike(like);
    }
    public void deleteArticleComment(Long CommentId) {
        articleDetailRemover.removeComment(CommentId);
    }
}
