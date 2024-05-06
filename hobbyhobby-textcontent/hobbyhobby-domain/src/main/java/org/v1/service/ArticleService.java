package org.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementaion.*;
import org.v1.model.Article;
import org.v1.model.Content;
import org.v1.model.Search;
import org.v1.model.User;
import org.v1.repository.S3Repository;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArticleService {
    private final ArticleReader articleReader;
    private final ArticleDetailReader articleDetailReader;
    private final ArticleAppender articleAppender;
    private final S3Repository s3Repository;
    private final ImageAppender imageAppender;
    private final ArticleRemover articleRemover;
    private final UserReader userReader;

    public List<Article> getTenArticle(Long communityId) {
        List<Article> articles = articleReader.readTenArticle(communityId);
        return articles.stream()
                .map(article -> {
                    User user = userReader.readUser(article.getUser().id());
                    return article.changeUser(user);
                })
                .collect(Collectors.toList());
    }
    public List<Article> getTenSearchArticle(Search search) {
        List<Article> articles = articleReader.readTenSearchArticle(search);
        return articles.stream()
                .map(article -> {
                    User user = userReader.readUser(article.getUser().id());
                    return article.changeUser(user);
                })
                .collect(Collectors.toList());
    }

    public Long createArticle(Article article, Content content) {
        Long articleId = articleAppender.appendArticle(article);
        List<Content.Image> imageList = content.getImages().stream()
                .map(image -> {
                    String path = imageAppender.appendImage(image.data(), articleId.toString());
                    return Content.Image.withoutData(image.index(), path);
                })
                .toList();
        articleAppender.appendArticleContent(new Content(content.getTexts(), imageList).calculateIndex(), articleId);
        return articleId;
    }
    public void deleteArticle(Long articleId){
        articleDetailReader.readContent(articleId).getImages().forEach(image -> {
            s3Repository.removeImage(image.path());
        });
        articleRemover.removeArticle(articleId);
    }
    public void createImage(File file) {
        s3Repository.uploadImage(file, "123");
    }
}
