package org.v1.service.gathering;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementaion.comment.CommentAppender;
import org.v1.implementaion.comment.CommentRemover;
import org.v1.model.comment.Comment;

@Service
@AllArgsConstructor
public class GatheringArticleCommentService {
    private final CommentRemover commentRemover;
    private final CommentAppender commentAppender;
    public Long createArticleComment(Comment comment, Long articleId) {
        return commentAppender.appendGatheringArticleComment(comment,articleId);
    }
    public void deleteArticleComment(Long commentId) {
        commentRemover.removeGatheringArticleComment(commentId);
    }
}
