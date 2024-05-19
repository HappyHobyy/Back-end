package org.v1.implementaion.comment;

import org.springframework.stereotype.Component;
import org.v1.model.comment.Comment;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentUpdater {
    public List<Comment> updateIsUserCommentOwner(List<Comment> comments,Long userId){
        return comments.parallelStream()
                .map(comment -> comment.changeUser(userId))
                .collect(Collectors.toList());
    }
}
