package org.v1.implementaion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.PhotoArticle;
import org.v1.repository.PhotoArticleRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class PhotoArticleReader {
    private PhotoArticleRepository photoArticleRepository;
    public List<PhotoArticle> readTenArticleLatest(Long communityId) {
        return photoArticleRepository.readArticleLatest(communityId);
    }
    public List<PhotoArticle> readTenArticleLikes(Long communityId) {
        return photoArticleRepository.readArticleLikes(communityId);
    }
}
