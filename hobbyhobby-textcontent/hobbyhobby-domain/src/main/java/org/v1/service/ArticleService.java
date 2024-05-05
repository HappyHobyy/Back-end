package org.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.model.Article;
import org.v1.model.Content;
import org.v1.model.Search;
import org.v1.implementaion.ArticleReader;
import org.v1.repository.S3Repository;

import java.io.File;
import java.util.List;

@Service
@AllArgsConstructor
public class ArticleService {
    private final ArticleReader articleReader;
    private final S3Repository s3Repository;
    public List<Article> getTenArticle(Long communityId) {
        return articleReader.readTenArticle(communityId);
    }
    public List<Article> getTenSearchArticle(Search search) {
        return articleReader.readTenSearchArticle(search);
    }
    public void createArticle(Article article, Content content) {
    }
    public void createImage(File file) {
        s3Repository.uploadImage(file,"123");
    }
}
