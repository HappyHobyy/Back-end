package org.v1.repository;

import org.v1.model.ArticleDetail;
import org.v1.model.Comment;
import org.v1.model.Like;

public interface ArticleDetailRepository {
    ArticleDetail read(Long id);
    void saveLike(Like like);
    void removeLike(Like like);
    void removeComment(Long CommentId);
    void saveComment(Comment comment);
}
