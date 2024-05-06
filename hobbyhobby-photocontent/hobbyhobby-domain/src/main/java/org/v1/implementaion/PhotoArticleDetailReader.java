package org.v1.implementaion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.Comment;
import org.v1.model.Content;
import org.v1.repository.PhotoArticleDetailRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class PhotoArticleDetailReader {
    private final PhotoArticleDetailRepository photoArticleDetailRepository;
    public Content readContent(Long photoArticleId) {
        return photoArticleDetailRepository.readContent(photoArticleId);
    }
    public List<Comment> readComments(Long photoArticleId) {
        return photoArticleDetailRepository.readComments(photoArticleId);
    }
}
