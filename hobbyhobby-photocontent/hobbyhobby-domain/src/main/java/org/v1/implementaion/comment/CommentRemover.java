package org.v1.implementaion.comment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.repository.gathering.GatheringCommentRepository;
import org.v1.repository.photo.PhotoCommentRepository;

@Component
@AllArgsConstructor
public class CommentRemover {
    private final PhotoCommentRepository photoCommentRepository;
    private final GatheringCommentRepository gatheringCommentRepository;
    public void removePhotoArticleComment(final Long commentId) {
        photoCommentRepository.removeComment(commentId);
    }
    public void removeGatheringArticleComment(final Long commentId) {
        gatheringCommentRepository.removeComment(commentId);
    }

}
