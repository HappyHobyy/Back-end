package org.v1.service.photoarticle;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementaion.comment.CommentAppender;
import org.v1.implementaion.comment.CommentRemover;
import org.v1.model.comment.Comment;
@Service
@AllArgsConstructor
public class PhotoArticleCommentService {
    private final CommentRemover commentRemover;
    private final CommentAppender commentAppender;
    public Long createArticleComment(Comment comment, Long photoArticleId) {
        return commentAppender.appendComment(comment,photoArticleId);
    }
    public void deleteArticleComment(Long commentId) {
        commentRemover.removeComment(commentId);
    }
}
