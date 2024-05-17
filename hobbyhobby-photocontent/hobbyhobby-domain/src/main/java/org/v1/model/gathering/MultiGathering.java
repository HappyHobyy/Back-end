package org.v1.model.gathering;

import org.v1.model.community.Community;

import java.util.List;

public record MultiGathering(
        GatheringArticle gatheringArticle,
        List<Community> communities
) {
}