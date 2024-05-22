package org.v1.dto;

import org.v1.model.community.Community;
import org.v1.model.content.GroupArticle;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

record GroupArticleResponse(
        Long id,
        LocalDateTime date,
        Long userId,
        String userNickName,
        String userImagePath,
        Integer likes,
        Integer comments,
        String firstImageUrl,
        List<CommunityResponse> communities
) {
    public record CommunityResponse(
            Integer id,
            String name
    ) {
        public static List<CommunityResponse> of(List<Community> communities) {
            return communities.stream()
                    .map(community -> new CommunityResponse(community.getId(), community.getCommunityName()))
                    .collect(Collectors.toList());
        }
    }

    static GroupArticleResponse fromGroupArticle(GroupArticle groupArticle) {
        return new GroupArticleResponse(
                groupArticle.getId(),
                groupArticle.getDate(),
                groupArticle.getUser().id(),
                groupArticle.getUser().nickname(),
                groupArticle.getUser().path(),
                groupArticle.getLikes(),
                groupArticle.getComments(),
                groupArticle.getFirstImageUrl(),
                CommunityResponse.of(groupArticle.getCommunityList())
        );
    }
}
