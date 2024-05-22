
package org.v1.implementaion.article;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.article.GatheringInfo;
import org.v1.repository.article.GatheringArticleRepository;

@Component
@AllArgsConstructor
public class GatheringArticleRemover {
    private GatheringArticleRepository repository;
    public void removeArticle(final GatheringInfo info) {
        repository.removeArticle(info);
    }
}
