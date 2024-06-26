package org.v1.service.article;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.v1.implementaion.article.*;
import org.v1.implementaion.comment.CommentReader;
import org.v1.implementaion.comment.CommentUpdater;
import org.v1.implementaion.imagevideo.ImageVideoManager;
import org.v1.implementaion.like.LikeChecker;
import org.v1.model.comment.Comment;
import org.v1.model.imageVideo.ImageVideo;
import org.v1.model.article.PhotoArticle;
import org.v1.model.like.Like;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PhotoArticleService {
    private final PhotoArticleReader photoArticleReader;
    private final PhotoArticleAppender photoArticleAppender;
    private final PhotoArticleRemover photoArticleRemover;
    private final ImageVideoManager imageVideoManager;
    private final PhotoArticleUpdater photoArticleUpdater;
    private final LikeChecker likeChecker;
    private final CommentReader commentReader;
    private final CommentUpdater commentUpdater;

    @Transactional
    public List<PhotoArticle> getTenArticleLatest(int index,int communityId, long userId) {
        return photoArticleReader.readTenArticleLatest(index,communityId).stream().map(
                photoArticle -> {
                    boolean isUserLiked = likeChecker.checkArticleLiked(Like.toPhotoLike(userId, photoArticle.getId()));
                    boolean isUserArticleOwner = photoArticle.isUserArticleOwner(userId);
                    return photoArticleUpdater.updateUserArticleRelation(photoArticle, isUserLiked, isUserArticleOwner);
                }
        ).collect(Collectors.toList());
    }
    @Transactional
    public List<PhotoArticle> getTenArticleLikes(int index,int communityId,long userId) {
        return photoArticleReader.readTenArticleLikes(index,communityId).stream().map(
                photoArticle -> {
                    boolean isUserLiked = likeChecker.checkArticleLiked(Like.toPhotoLike(userId, photoArticle.getId()));
                    boolean isUserArticleOwner = photoArticle.isUserArticleOwner(userId);
                    return photoArticleUpdater.updateUserArticleRelation(photoArticle, isUserLiked, isUserArticleOwner);
                }
        ).collect(Collectors.toList());
    }
    public long createArticle(PhotoArticle photoArticle) {
        long photoArticleId = photoArticleAppender.appendArticle(photoArticle);
        List<ImageVideo> imageVideos = imageVideoManager.appendFiles(photoArticleId, photoArticle.getContent().getImages());
        photoArticleAppender.appendArticleContent(imageVideos, photoArticleId);
        return photoArticleId;
    }
    public void deleteArticle(long photoArticleId){
        imageVideoManager.removeImages(photoArticleReader.readImageList(photoArticleId));
        photoArticleRemover.removeArticle(photoArticleId);
    }
    public  List<Comment> getArticleComment(long photoArticleId, long userId) {
        List<Comment> comments = commentReader.readPhotoArticleComments(photoArticleId);
        return commentUpdater.updateIsUserCommentOwner(comments,userId);
    }
}
