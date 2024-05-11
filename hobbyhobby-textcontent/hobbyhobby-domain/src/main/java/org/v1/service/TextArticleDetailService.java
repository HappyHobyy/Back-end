package org.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementaion.comment.TextCommentAppender;
import org.v1.implementaion.comment.TextCommentReader;
import org.v1.implementaion.comment.TextCommentRemover;
import org.v1.implementaion.like.LikeAppender;
import org.v1.implementaion.like.LikeChecker;
import org.v1.implementaion.like.LikeRemover;
import org.v1.implementaion.textarticle.TextArticleChecker;
import org.v1.implementaion.textarticle.TextArticleReader;
import org.v1.model.*;

import java.util.List;

@Service
@AllArgsConstructor
public class TextArticleDetailService {

    private final TextArticleChecker textArticleChecker;
    private final TextArticleReader textArticleReader;
    private final LikeChecker likeChecker;
    private final LikeAppender likeAppender;
    private final LikeRemover likeRemover;
    private final TextCommentReader textCommentReader;
    private final TextCommentAppender textCommentAppender;
    private final TextCommentRemover textCommentRemover;
    public TextArticleDetail getArticleDetail(Long articleId, Long userId) {
        UserStatus userStatus = textArticleChecker.checkArticleUserRelation(articleId, userId);
        List<TextComment> comments = textCommentReader.readComments(articleId);
        List<TextComment> updatedComments = comments.parallelStream()
                .map(textComment -> textComment.changeComment(textComment.getComment().validateUserComment(userId)))
                .toList();
        Content content = textArticleReader.readContent(articleId);
        return new TextArticleDetail(updatedComments, content, userStatus);
    }
    public void likeArticle(Like like) {
        likeChecker.checkArticleLikeToAppend(like);
        likeAppender.appendLike(like);
    }
    public Long commentOnArticle(TextComment comment, Long articleId) {
        textArticleChecker.checkArticleExist(articleId);
        return textCommentAppender.appendComment(comment, articleId);
    }
    public void unlikeArticle(Like like) {
        likeChecker.checkArticleLikeToRemove(like);
        likeRemover.removeLike(like);
    }
    public void deleteArticleComment(Long commentId) {
        textCommentRemover.removeComment(commentId);
    }
}
