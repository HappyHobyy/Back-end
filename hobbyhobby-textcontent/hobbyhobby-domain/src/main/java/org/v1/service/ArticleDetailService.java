package org.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementaion.ArticleDetailAppender;
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
    public ArticleDetail getArticleDetail(Long textContentId) {
        return articleDetailReader.read(textContentId);
    }
    public void createArticleLike(Like like) {
        articleDetailAppender.appendLike(like);
    }
    public void createArticleComment(Comment comment) {
        articleDetailAppender.appendComment(comment);
    }
    public void deleteArticleLike(Like like) {
        articleDetailRemover.removeLike(like);
    }
    public void deleteArticleComment(Long CommentId) {
        articleDetailRemover.removeComment(CommentId);
    }
}
