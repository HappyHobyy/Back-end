package org.v1.implementaion.textarticle;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.Content;
import org.v1.model.TextArticle;
import org.v1.repository.TextArticleRepository;
import org.v1.model.Search;

import java.util.List;

@Component
@AllArgsConstructor
public class TextArticleReader {
    private TextArticleRepository textArticleRepository;
    public List<TextArticle> readTenArticle(Long communityId) {
        return textArticleRepository.readArticleByCommunity(communityId);
    }
    public List<TextArticle> readTenSearchArticle(Search search) {
        return textArticleRepository.readArticleBySearch(search);
    }
    public Content readContent(Long articleId) {
        return textArticleRepository.readTextContent(articleId);
    }
}
