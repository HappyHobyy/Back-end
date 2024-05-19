package org.v1.model.gatheringarticle;

import org.v1.model.community.Community;
import org.v1.model.gatheringarticle.GatheringArticle;

public record SingleGathering(
        GatheringArticle gatheringArticle,
        Community community
) {
}
