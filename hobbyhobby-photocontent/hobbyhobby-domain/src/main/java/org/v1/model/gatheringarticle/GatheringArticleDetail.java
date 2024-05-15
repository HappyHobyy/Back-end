package org.v1.model.gatheringarticle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.v1.model.comment.Comment;
import org.v1.model.photoartlcle.PhotoAriticleContent;
import org.v1.model.user.UserStatus;

import java.util.List;
@Getter
@AllArgsConstructor
public class GatheringArticleDetail {
    private final PhotoAriticleContent photoAriticleContent;
    private final UserStatus userStatus;
}