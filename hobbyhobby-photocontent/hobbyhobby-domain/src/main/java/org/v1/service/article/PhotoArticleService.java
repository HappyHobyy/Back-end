package org.v1.service.article;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementaion.comment.CommentReader;
import org.v1.implementaion.comment.CommentUpdater;
import org.v1.implementaion.imagevideo.ImageVideoManager;
import org.v1.implementaion.article.PhotoArticleAppender;
import org.v1.implementaion.article.PhotoArticleChecker;
import org.v1.implementaion.article.PhotoArticleReader;
import org.v1.implementaion.article.PhotoArticleRemover;
import org.v1.model.comment.Comment;
import org.v1.model.imageVideo.ImageVideo;
import org.v1.model.article.PhotoArticle;
import org.v1.model.article.PhotoAriticleContent;
import org.v1.model.article.PhotoArticleDetail;
import org.v1.model.user.UserStatus;

import java.util.List;

@Service
@AllArgsConstructor
public class PhotoArticleService {
    private final PhotoArticleReader photoArticleReader;
    private final PhotoArticleAppender photoArticleAppender;
    private final PhotoArticleRemover photoArticleRemover;
    private final ImageVideoManager imageVideoManager;
    private final PhotoArticleChecker photoArticleChecker;
    private final CommentReader commentReader;
    private final CommentUpdater commentUpdater;

    public List<PhotoArticle> getTenArticleLatest(Integer communityId) {
        return photoArticleReader.readTenArticleLatest(communityId);
    }
    public List<PhotoArticle> getTenArticleLikes(Integer communityId) {
        return  photoArticleReader.readTenArticleLikes(communityId);
    }
    public Long createArticle(PhotoArticle photoArticle, PhotoAriticleContent photoAriticleContent) {
        Long photoArticleId = photoArticleAppender.appendArticle(photoArticle);
        List<ImageVideo> imageVideos = imageVideoManager.appendFiles(photoArticleId, photoAriticleContent.getImages());
        photoArticleAppender.appendArticleContent(new PhotoAriticleContent(photoAriticleContent.getText(), imageVideos), photoArticleId);
        return photoArticleId;
    }
    public void deleteArticle(Long photoArticleId){
        imageVideoManager.removeImages(photoArticleReader.readContent(photoArticleId).getImages());
        photoArticleRemover.removeArticle(photoArticleId);
    }
    public PhotoArticleDetail getArticleDetail(Long photoArticleId, Long userId) {
        UserStatus userStatus = photoArticleChecker.checkArticleUserRelation(photoArticleId, userId);
        List<Comment> comments = commentReader.readPhotoArticleComments(photoArticleId);
        List<Comment> updatedComments = commentUpdater.updateIsUserCommentOwner(comments,userId);
        PhotoAriticleContent photoAriticleContent = photoArticleReader.readContent(photoArticleId);
        return new PhotoArticleDetail(updatedComments, photoAriticleContent, userStatus);
    }
}
