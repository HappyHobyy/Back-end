package org.v1.model.gathering;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.v1.model.photo.PhotoAriticleContent;
import org.v1.model.user.UserStatus;

@Getter
@AllArgsConstructor
public class GatheringArticleDetail {
    private final PhotoAriticleContent photoAriticleContent;
    private final UserStatus userStatus;
}