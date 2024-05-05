package org.v1.implementaion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.Content;
import org.v1.repository.ArticleDetailRepository;
import org.v1.model.ArticleDetail;

@Component
@AllArgsConstructor
public class ArticleDetailReader {
    private final ArticleDetailRepository articleDetailRepository;
    public ArticleDetail read(Long textContentId) {
        return articleDetailRepository.read(textContentId);
    }
    public Content readContent(Long articleId) {
        return articleDetailRepository.readContent(articleId);
    }
}
