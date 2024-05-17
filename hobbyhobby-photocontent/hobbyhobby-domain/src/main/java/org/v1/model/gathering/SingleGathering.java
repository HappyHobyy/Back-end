package org.v1.model.gathering;

import org.v1.model.community.Community;

public record SingleGathering(
        GatheringArticle gatheringArticle,
        Community community
) {
}
