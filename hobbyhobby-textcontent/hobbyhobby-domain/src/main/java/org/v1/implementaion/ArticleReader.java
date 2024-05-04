package org.v1.implementaion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.repository.ArticleRepository;
import org.v1.model.Article;
import org.v1.model.Search;

import java.util.List;

@Component
@AllArgsConstructor
public class ArticleReader {
    private ArticleRepository articleRepository;
    public List<Article> readTenArticle(Long communityId) {
        return articleRepository.getArticleByCommunity(communityId);
    }
    public List<Article> readTenSearchArticle(Search search) {
        return articleRepository.getArticleBySearch(search);
    }
}
