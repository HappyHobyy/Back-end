package org.v1.repository;

import org.v1.model.TextComment;

import java.util.List;

public interface TextCommentRepository {
    List<TextComment> readComments(Long articleId);
    Long appendComment(TextComment textComment,Long articleId);
    void removeComment(Long commentId);
}
