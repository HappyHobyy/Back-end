package org.v1.controller;

import org.v1.model.Search;

public record ArticleRequest(
        LatestRequest latest ,
        SearchRequest search
) {
    public record LatestRequest(Long communityId) {}
    public record SearchRequest(Long communityId,String search) {
        public Search toSearch() {
            return new Search(communityId, search);
        }
    }
}
