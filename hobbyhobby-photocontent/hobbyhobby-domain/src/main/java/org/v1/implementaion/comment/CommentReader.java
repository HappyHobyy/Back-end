package org.v1.implementaion.comment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.comment.Comment;
import org.v1.repository.gathering.GatheringCommentRepository;
import org.v1.repository.photo.PhotoCommentRepository;
import java.util.List;

@Component
@AllArgsConstructor
public class CommentReader {
    private final PhotoCommentRepository photoCommentRepository;
    private final GatheringCommentRepository gatheringCommentRepository;
    public List<Comment> readPhotoArticleComments(Long articleId) {
        return photoCommentRepository.readComments(articleId);
    }
    public List<Comment> readGatheringArticleComments(Long articleId) {
        return gatheringCommentRepository.readComments(articleId);
    }
}
