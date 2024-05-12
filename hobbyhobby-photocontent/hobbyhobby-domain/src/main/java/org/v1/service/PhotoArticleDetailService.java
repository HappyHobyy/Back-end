package org.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementaion.comment.CommentAppender;
import org.v1.implementaion.comment.CommentReader;
import org.v1.implementaion.comment.CommentRemover;
import org.v1.implementaion.like.LikeAppender;
import org.v1.implementaion.like.LikeChecker;
import org.v1.implementaion.like.LikeEventPublisher;
import org.v1.implementaion.like.LikeRemover;
import org.v1.implementaion.photoarticle.PhotoArticleChecker;
import org.v1.implementaion.photoarticle.PhotoArticleReader;
import org.v1.model.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PhotoArticleDetailService {
    private final PhotoArticleChecker photoArticleChecker;
    private final PhotoArticleReader photoArticleReader;
    private final CommentReader commentReader;
    private final CommentRemover commentRemover;
    private final CommentAppender commentAppender;
    private final LikeAppender likeAppender;
    private final LikeChecker likeChecker;
    private final LikeRemover likeRemover;
    private final LikeEventPublisher likeEventPublisher;
    public PhotoArticleDetail getArticleDetail(Long photoArticleId, Long userId) {
        UserStatus userStatus = photoArticleChecker.checkArticleUserRelation(photoArticleId, userId);
        List<Comment> comments = commentReader.readComments(photoArticleId);
        List<Comment> updatedComments = comments.parallelStream()
                .map(comment -> comment.changeUser(userId))
                .collect(Collectors.toList());
        Content content = photoArticleReader.readContent(photoArticleId);
        return new PhotoArticleDetail(updatedComments, content, userStatus);
    }
    public Long createArticleComment(Comment comment,Long photoArticleId) {
        photoArticleChecker.checkArticleExist(photoArticleId);
        return commentAppender.appendComment(comment,photoArticleId);
    }
    public void deleteArticleComment(Long commentId) {
        commentRemover.removeComment(commentId);
    }
    public void createArticleLike(Like like) {
        likeChecker.checkArticleLikeToAppend(like);
        Integer communityId = likeAppender.appendLike(like);
        likeEventPublisher.publishLikeEvent(communityId);
    }
    public void deleteArticleLike(Like like) {
        likeChecker.checkArticleLikeToRemove(like);
        Integer communityId = likeRemover.removeLike(like);
        likeEventPublisher.publishDisLikeEvent(communityId);
    }
}
