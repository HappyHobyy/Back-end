package org.v1.repository;

import org.v1.model.PhotoArticleDetail;
import org.v1.model.Comment;
import org.v1.model.Content;
import org.v1.model.Like;

import java.util.List;

public interface PhotoArticleDetailRepository {
    PhotoArticleDetail read(Long id);
    PhotoArticleDetail.UserStatus checkArticleUserRelation(Long photoArticleId, Long userId);
    boolean checkArticleExist(Long photoArticleId);
    boolean checkArticleLike(Like like);
    void saveLike(Like like);
    void removeLike(Like like);
    void removeComment(Long CommentId);
    Long saveComment(Comment comment,Long photoArticleId);
    Content readContent(Long photoArticleId);
    List<Comment> readComments(Long photoArticleId);
}
