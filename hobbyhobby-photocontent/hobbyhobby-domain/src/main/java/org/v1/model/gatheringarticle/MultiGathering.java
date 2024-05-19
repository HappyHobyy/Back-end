package org.v1.model.gatheringarticle;

import org.v1.model.community.Community;
import org.v1.model.gatheringarticle.GatheringArticle;

import java.util.List;

public record MultiGathering(
        GatheringArticle gatheringArticle,
        List<Community> communities
) {
}