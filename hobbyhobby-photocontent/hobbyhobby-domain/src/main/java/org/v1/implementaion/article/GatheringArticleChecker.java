package org.v1.implementaion.article;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.article.GatheringInfo;
import org.v1.model.user.UserStatus;
import org.v1.repository.article.GatheringArticleRepository;

@Component
@AllArgsConstructor
public class GatheringArticleChecker {
    private final GatheringArticleRepository repository;
    public UserStatus checkArticleUserRelation(Long userId, GatheringInfo info) {
        return repository.checkArticleUserRelation(info, userId);
    }
}
