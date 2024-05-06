package org.v1.implementaion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.Comment;
import org.v1.model.Content;
import org.v1.repository.ArticleDetailRepository;
import org.v1.model.ArticleDetail;

import java.util.List;

@Component
@AllArgsConstructor
public class ArticleDetailReader {
    private final ArticleDetailRepository articleDetailRepository;
    public Content readContent(Long articleId) {
        return articleDetailRepository.readContent(articleId);
    }
    public List<Comment> readComments(Long articleId) {
        return articleDetailRepository.readComments(articleId);
    }
}
