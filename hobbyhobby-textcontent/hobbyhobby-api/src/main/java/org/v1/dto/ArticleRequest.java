package org.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.v1.model.Search;

public record ArticleRequest(
        LatestRequest latest,
        SearchRequest search
) {
    public record LatestRequest(
            @Schema(description = "커뮤니티Id", example = "123")
            @NotNull(message = "커뮤니티Id는 필수 입력값입니다.")
            Long communityId
    ) {}
    public record SearchRequest(
            @Schema(description = "커뮤니티Id", example = "123")
            @NotNull(message = "커뮤니티Id는 필수 입력값입니다.")
            Long communityId,
            @Schema(description = "검색명", example = "123")
            @NotNull(message = "검색명은 필수 입력값입니다.")
            String search
    ) {
        public Search toSearch() {
            return new Search(communityId, search);
        }
    }
}
