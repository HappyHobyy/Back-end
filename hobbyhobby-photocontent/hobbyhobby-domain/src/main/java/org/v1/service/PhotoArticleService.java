package org.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementaion.comment.CommentCounter;
import org.v1.implementaion.imagevideo.ImageVideoProcessor;
import org.v1.implementaion.like.LikeCounter;
import org.v1.implementaion.photoarticle.PhotoArticleAppender;
import org.v1.implementaion.photoarticle.PhotoArticleReader;
import org.v1.implementaion.photoarticle.PhotoArticleRemover;
import org.v1.model.PhotoArticle;
import org.v1.model.Content;

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
    public Long createArticle(PhotoArticle photoArticle, Content content) {
        Long photoArticleId = photoArticleAppender.appendPhotoArticle(photoArticle);
        List<Content.ImageVideo> imageVideos = imageVideoProcessor.appendFiles(photoArticleId, content.getImages());
        photoArticleAppender.appendPhotoArticleContent(new Content(content.getText(), imageVideos), photoArticleId);
        return photoArticleId;
    }
    public void deleteArticle(Long photoArticleId){
        imageVideoProcessor.removeImages(photoArticleReader.readContent(photoArticleId).getImages());
        photoArticleRemover.removeArticle(photoArticleId);
    }
}
