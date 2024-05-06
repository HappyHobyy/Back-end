package org.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementaion.*;
import org.v1.implementaion.ImageVideoAppender;
import org.v1.implementaion.ImageVideoRemover;
import org.v1.implementaion.PhotoArticleAppender;
import org.v1.implementaion.PhotoArticleReader;
import org.v1.model.PhotoArticle;
import org.v1.model.Content;
import org.v1.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PhotoArticleService {
    private final PhotoArticleReader photoArticleReader;
    private final PhotoArticleDetailReader photoArticleDetailReader;
    private final PhotoArticleAppender photoArticleAppender;
    private final PhotoArticleRemover photoArticleRemover;
    private final ImageVideoRemover imageVideoRemover;
    private final ImageVideoAppender imageVideoAppender;
    private final UserReader userReader;

    public List<PhotoArticle> getTenArticleLatest(Long communityId) {
        List<PhotoArticle> photoArticles = photoArticleReader.readTenArticleLatest(communityId);
        return photoArticles.stream()
                .map(photoArticle -> {
                    User user = userReader.readUser(photoArticle.getUser().id());
                    return photoArticle.changeUser(user);
                })
                .collect(Collectors.toList());
    }
    public List<PhotoArticle> getTenArticleLikes(Long communityId) {
        List<PhotoArticle> photoArticles = photoArticleReader.readTenArticleLikes(communityId);
        return photoArticles.stream()
                .map(photoArticle -> {
                    User user = userReader.readUser(photoArticle.getUser().id());
                    return photoArticle.changeUser(user);
                })
                .collect(Collectors.toList());
    }
    public Long createArticle(PhotoArticle photoArticle, Content content) {
        Long photoArticleId = photoArticleAppender.appendPhotoArticle(photoArticle);
        List<Content.ImageVideo> fileList = content.getImages().stream()
                .map(imageVideo -> {
                    String path = imageVideoAppender.appendFile(imageVideo.data(), photoArticleId.toString());
                    return Content.ImageVideo.withoutData(imageVideo.index(), path);
                })
                .toList();
        photoArticleAppender.appendPhotoArticleContent(new Content(content.getText(), fileList), photoArticleId);
        return photoArticleId;
    }
    public void deleteArticle(Long photoArticleId){
        photoArticleDetailReader.readContent(photoArticleId).getImages().forEach(imageVideo -> {
            imageVideoRemover.removeFile(imageVideo.path());
        });
        photoArticleRemover.removeArticle(photoArticleId);
    }
}
