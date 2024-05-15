package org.v1.service.photoarticle;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementaion.comment.CommentCounter;
import org.v1.implementaion.comment.CommentReader;
import org.v1.implementaion.imagevideo.ImageVideoProcessor;
import org.v1.implementaion.like.LikeCounter;
import org.v1.implementaion.photoarticle.PhotoArticleAppender;
import org.v1.implementaion.photoarticle.PhotoArticleChecker;
import org.v1.implementaion.photoarticle.PhotoArticleReader;
import org.v1.implementaion.photoarticle.PhotoArticleRemover;
import org.v1.model.comment.Comment;
import org.v1.model.photoartlcle.PhotoArticle;
import org.v1.model.photoartlcle.PhotoAriticleContent;
import org.v1.model.photoartlcle.PhotoArticleDetail;
import org.v1.model.user.UserStatus;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PhotoArticleService {
    private final PhotoArticleReader photoArticleReader;
    private final PhotoArticleAppender photoArticleAppender;
    private final PhotoArticleRemover photoArticleRemover;
    private final ImageVideoProcessor imageVideoProcessor;
    private final LikeCounter likeCounter;
    private final CommentCounter commentCounter;
    private final PhotoArticleChecker photoArticleChecker;
    private final CommentReader commentReader;

    public List<PhotoArticle> getTenArticleLatest(Integer communityId) {
        return photoArticleReader.readTenArticleLatest(communityId)
                .parallelStream()
                .map(photoArticle -> {
                    PhotoArticle.LikesComments likesComments = new PhotoArticle.LikesComments(
                            likeCounter.countLike(photoArticle.getId()),
                            commentCounter.countComment(photoArticle.getId())
                    );
                    return photoArticle.withLikesComments(likesComments);
                })
                .collect(Collectors.toList());
    }
    public List<PhotoArticle> getTenArticleLikes(Integer communityId) {
        return  photoArticleReader.readTenArticleLikes(communityId);
    }
    public Long createArticle(PhotoArticle photoArticle, PhotoAriticleContent photoAriticleContent) {
        Long photoArticleId = photoArticleAppender.appendPhotoArticle(photoArticle);
        List<PhotoAriticleContent.ImageVideo> imageVideos = imageVideoProcessor.appendFiles(photoArticleId, photoAriticleContent.getImages());
        photoArticleAppender.appendPhotoArticleContent(new PhotoAriticleContent(photoAriticleContent.getText(), imageVideos), photoArticleId);
        return photoArticleId;
    }
    public void deleteArticle(Long photoArticleId){
        imageVideoProcessor.removeImages(photoArticleReader.readContent(photoArticleId).getImages());
        photoArticleRemover.removeArticle(photoArticleId);
    }
    public PhotoArticleDetail getArticleDetail(Long photoArticleId, Long userId) {
        UserStatus userStatus = photoArticleChecker.checkArticleUserRelation(photoArticleId, userId);
        List<Comment> comments = commentReader.readComments(photoArticleId);
        List<Comment> updatedComments = comments.parallelStream()
                .map(comment -> comment.changeUser(userId))
                .collect(Collectors.toList());
        PhotoAriticleContent photoAriticleContent = photoArticleReader.readContent(photoArticleId);
        return new PhotoArticleDetail(updatedComments, photoAriticleContent, userStatus);
    }
}
