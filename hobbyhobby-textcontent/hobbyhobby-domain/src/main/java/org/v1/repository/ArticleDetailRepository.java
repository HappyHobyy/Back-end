package org.v1.repository;

import org.v1.model.ArticleDetail;
import org.v1.model.Comment;
import org.v1.model.Content;
import org.v1.model.Like;

public interface ArticleDetailRepository {
    ArticleDetail read(Long id);
    ArticleDetail.UserStatus checkArticleUserRelation(Long articleId, Long userId);
    boolean checkArticleExist(Long articleId);
    boolean checkArticleLike(Like like);
    void saveLike(Like like);
    void removeLike(Like like);
    void removeComment(Long CommentId);
    void saveComment(Comment comment,Long articleId);
    Content readContent(Long articleId);
}
