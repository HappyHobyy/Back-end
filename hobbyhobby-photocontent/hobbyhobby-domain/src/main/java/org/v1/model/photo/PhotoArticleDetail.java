package org.v1.model.photo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.v1.model.comment.Comment;
import org.v1.model.user.UserStatus;

import java.util.List;

@Getter
@AllArgsConstructor
public class PhotoArticleDetail {
    private final List<Comment> comments;
    private final PhotoAriticleContent photoAriticleContent;
    private final UserStatus userStatus;
}
