package org.v1.implementaion.comment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.repository.gathering.GatheringCommentRepository;
import org.v1.repository.photo.PhotoCommentRepository;

@Component
@AllArgsConstructor
public class CommentCounter {
    private final PhotoCommentRepository photoCommentRepository;
    private final GatheringCommentRepository gatheringCommentRepository;
    public Integer countPhotoArticleComment(Long articleId) {
        return photoCommentRepository.countComment(articleId);
    }
    public Integer countGatheringArticleComment(Long articleId) {
        return gatheringCommentRepository.countComment(articleId);
    }

}
