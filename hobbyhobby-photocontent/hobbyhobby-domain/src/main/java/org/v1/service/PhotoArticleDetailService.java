package org.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementaion.*;
import org.v1.model.*;

import java.util.List;

@Service
@AllArgsConstructor
public class PhotoArticleDetailService {
    private final PhotoArticleDetailReader photoArticleDetailReader;
    private final PhotoArticleDetailAppender photoArticleDetailAppender;
    private final PhotoArticleDetailRemover photoArticleDetailRemover;
    private final PhotoArticleDetailChecker photoArticleDetailChecker;
    private final UserReader userReader;
    public PhotoArticleDetail getArticleDetail(Long photoArticleId, Long userId) {
        PhotoArticleDetail.UserStatus userStatus = photoArticleDetailChecker.checkArticleUserRelation(photoArticleId,userId);
        List<Comment> comments = photoArticleDetailReader.readComments(photoArticleId);
        List<Comment> updatedComments = comments.stream()
                .map(comment -> {
                    User commentUser = userReader.readUser(comment.getUser().id());
                    return comment.changeUser(commentUser,userId);
                })
                .toList();
        Content content = photoArticleDetailReader.readContent(photoArticleId);
        return new PhotoArticleDetail(updatedComments,content,userStatus);
    }
    public void createArticleLike(Like like) {
        photoArticleDetailChecker.checkArticleLikeToAppend(like);
        photoArticleDetailAppender.appendLike(like);
    }
    public Long createArticleComment(Comment comment,Long photoArticleId) {
        photoArticleDetailChecker.checkArticleExist(photoArticleId);
        return photoArticleDetailAppender.appendComment(comment,photoArticleId);
    }
    public void deleteArticleLike(Like like) {
        photoArticleDetailChecker.checkArticleLikeToRemove(like);
        photoArticleDetailRemover.removeLike(like);
    }
    public void deleteArticleComment(Long CommentId) {
        photoArticleDetailRemover.removeComment(CommentId);
    }
}
